package com.ice.encryption;

import com.alibaba.fastjson.JSON;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据加密解密demo
 * --这里以数据校验业务接口为例
 * @ClassName Demo
 * @Description TODO
 * @Author liubin
 * @Date 2019/7/24 4:46 PM
 **/
public class Demo {

    //第三方提供的公钥
    private static String CHANNEL_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD3X24VEZRObGxPNj/OmNt23KjaAQESixiUy2QtZUlEvPs82qCYTqXwSgCvYSDbejMWMJfGvdoWgZahFzbee3jPt8cJPZiKwD0nLvYiT9ATXm7HPvxgWguMHJw/1UELrdYgQCPPgvsXGpm6JTY6qODTxsw7cJiLM1OnfJ4nboVMNwIDAQAB";
    //我方
    private static String ANNIU_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIbbowhZ5zUCcltO43Bhb+A1Q1GJGduNdpdeexOlI/CZFtLABLlX4dDE0RHZVWLDb2ZUpl1n8Fd8bcCqoGddDzTtqovUWPRRAPzQx5XPe+1g1Pc5yFGP46dUW4PwD++LjSoNJcRQK0AFQ3WQulrS7ZR5aAW0UFLVjNninVFvcSeTAgMBAAECgYAYRvXkjPq4akZX9a2wD3pqbGxAA2RqR98vhMy3TqVXrNeU/QhcrVtzj9rrUaqzaudlDpwceJelyDqh0pWpxfD0nDLFH3hfpMbRUDig5vj+FpIf8A1O6MocaqbxG/CWcBH4vO6ypgn9bbHM1vk3x5QIGYcEHPc1A0ztbbuZpbn3QQJBAOCFNQcVi2UW2IbNyHziZs4dL92j6RyaMuGc8BpQoPcX+eghvxpzWrXMLN6txsn2ZUNuaM4smy+4aatb+2OMBXECQQCZxCTjzCFVqd5agumrb5/W+h88QvtvOpY88iN147WA9qrwYxzYZomedy+CIyv+pCESqz7QdKdRxVH/QCJrHetDAkA+mqYYnZdKzr39iK/YssB7xIOuR6Aei5iYdEw26jiX7aWPZqmE1Jl8i7wPM2F0uzGtRcIPizySZ9d/IpzMOyThAkA1XPTQeY84A3O/RZ+Cd4KpQLSGXViVCWgKTp4v046x6uj38+2WzD8GV6aNK1ehT6SKrhNmlJVcs3X5d3B6bC+xAkANxEwL2Be9xkCYPx4vu7dzpS3D7L48/MvGlnSnCBJktrrT0SrcZYAjIXqaZL9Mu8TmKUDqJgo+OqElV8wgNNq4";
    private static String ANNIU_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG26MIWec1AnJbTuNwYW/gNUNRiRnbjXaXXnsTpSPwmRbSwAS5V+HQxNER2VViw29mVKZdZ/BXfG3AqqBnXQ807aqL1Fj0UQD80MeVz3vtYNT3OchRj+OnVFuD8A/vi40qDSXEUCtABUN1kLpa0u2UeWgFtFBS1YzZ4p1Rb3EnkwIDAQAB";


    public static void main(String[] args) throws Exception {
        //业务原始数据JSON
        String bizData = "{\"name\":\"水润杰\",\"id_card\":\"33022719901010681x\",\"phone\":\"13758116235\",\"product_id\":76}";
        //1.AES加密业务数据
        //a.先生成一个随机的aeskey
        String aesKey = generateKey();
        //b.aes加密数据
        String bizDataE = encrypt(bizData,aesKey);
        //2.rsa加密aeskey生成enckey
        String encKey = encryptByPublicKey(CHANNEL_PUBLIC_KEY,aesKey);
        //3.利用我方rsa私钥加签
        String sign = sign(bizDataE,ANNIU_PRIVATE_KEY);
        //生成最终数据
        Map<String,Object> data = new HashMap<>(16);
        data.put("biz_data",bizDataE);
        data.put("sign",sign);
        data.put("biz_enc","AES");
        data.put("enc_key",encKey);
        System.out.println(JSON.toJSONString(data));
    }

    /**
     * -------------------------
     * -------------------------
     * 下面为加密的一些辅助方法
     */

    /**
     * 生成签名
     *
     * @param data   签名数据
     * @param priKey base64私钥
     * @return 签的base64
     */
    public static String sign(String data, String priKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64Util.decode(priKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes(Charset.defaultCharset().name()));
        byte[] sign = signature.sign();
        return Base64Util.encode(sign);
    }

    /**
     * 生产aes秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("aes");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        String key = Base64Util.encode(secretKey.getEncoded());
        return key;
    }

    /**
     * aes加密
     *
     * @param dataStr 原始数据
     * @param keyStr  base64的秘钥
     * @return 加密数据base64
     */
    public static String encrypt(String dataStr, String keyStr) throws Exception {
        Key key = new SecretKeySpec(Base64Util.decode(keyStr), "aes");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);//加密模式
        byte[] result = cipher.doFinal(dataStr.getBytes(Charset.defaultCharset().name()));
        return Base64Util.encode(result);
    }

    /**
     * RAS公钥加密
     *
     * @param pubKey base64公钥
     * @param data   原始数据
     * @return 加密后的base64
     */
    public static String encryptByPublicKey(String pubKey, String data) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64Util.decode(pubKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(data.getBytes());
        System.out.println(Base64Util.encode(bytes));
        byte[] output = blockCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(Charset.defaultCharset().name()));
        System.out.println(Base64Util.encode(output));
        return Base64Util.encode(output);
    }

    /**
     * 数据分段加解密
     *
     * @param cipher
     * @param mode
     * @param dataByte
     * @return
     * @throws IOException
     */
    private static byte[] blockCodec(Cipher cipher, int mode, byte[] dataByte) throws IOException {
        int maxBlock;
        if (mode == Cipher.DECRYPT_MODE) {
            maxBlock = 128;
        } else {
            maxBlock = 117;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (dataByte.length > offSet) {
                if (dataByte.length - offSet > maxBlock) {
                    buff = cipher.doFinal(dataByte, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(dataByte, offSet, dataByte.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            byte[] resultBytes = out.toByteArray();
            return resultBytes;
        } catch (Exception e) {
            throw new RuntimeException("加解密块大小为[" + maxBlock + "]时发生异常", e);
        } finally {
            out.close();
        }
    }
    public static class Base64Util {
        private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
                .toCharArray();

        private static final char last2byte = (char) Integer
                .parseInt("00000011", 2);
        private static final char last4byte = (char) Integer
                .parseInt("00001111", 2);
        private static final char last6byte = (char) Integer
                .parseInt("00111111", 2);
        private static final char lead6byte = (char) Integer
                .parseInt("11111100", 2);
        private static final char lead4byte = (char) Integer
                .parseInt("11110000", 2);
        private static final char lead2byte = (char) Integer
                .parseInt("11000000", 2);
        private static final char[] encodeTable = new char[] { 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9', '+', '/' };

        private static int[]  toInt   = new int[128];

        static {
            for (int i = 0; i < ALPHABET.length; i++) {
                toInt[ALPHABET[i]] = i;
            }
        }

        /**
         * Base64 encoding.
         *
         * @param from
         *            The src data.
         * @return cryto_str
         */
        public static String encode(byte[] from) {
            StringBuilder to = new StringBuilder((int) (from.length * 1.34) + 3);
            int num = 0;
            char currentByte = 0;
            for (int i = 0; i < from.length; i++) {
                num = num % 8;
                while (num < 8) {
                    switch (num) {
                        case 0:
                            currentByte = (char) (from[i] & lead6byte);
                            currentByte = (char) (currentByte >>> 2);
                            break;
                        case 2:
                            currentByte = (char) (from[i] & last6byte);
                            break;
                        case 4:
                            currentByte = (char) (from[i] & last4byte);
                            currentByte = (char) (currentByte << 2);
                            if ((i + 1) < from.length) {
                                currentByte |= (from[i + 1] & lead2byte) >>> 6;
                            }
                            break;
                        case 6:
                            currentByte = (char) (from[i] & last2byte);
                            currentByte = (char) (currentByte << 4);
                            if ((i + 1) < from.length) {
                                currentByte |= (from[i + 1] & lead4byte) >>> 4;
                            }
                            break;
                        default:
                            break;
                    }
                    to.append(encodeTable[currentByte]);
                    num += 6;
                }
            }
            if (to.length() % 4 != 0) {
                for (int i = 4 - to.length() % 4; i > 0; i--) {
                    to.append("=");
                }
            }
            return to.toString();
        }

        /**
         * Translates the specified Base64 string into a byte array.
         *
         * @param s the Base64 string (not null)
         * @return the byte array (not null)
         */
        public static byte[] decode(String s) {
            int delta = s.endsWith( "==" ) ? 2 : s.endsWith( "=" ) ? 1 : 0;
            byte[] buffer = new byte[s.length() * 3 / 4 - delta];
            int mask = 0xFF;
            int index = 0;
            for (int i = 0; i < s.length(); i += 4) {
                int c0 = toInt[s.charAt( i )];
                int c1 = toInt[s.charAt( i + 1)];
                buffer[index++] = (byte)(((c0 << 2) | (c1 >> 4)) & mask);
                if (index >= buffer.length) {
                    return buffer;
                }
                int c2 = toInt[s.charAt( i + 2)];
                buffer[index++] = (byte)(((c1 << 4) | (c2 >> 2)) & mask);
                if (index >= buffer.length) {
                    return buffer;
                }
                int c3 = toInt[s.charAt( i + 3 )];
                buffer[index++] = (byte)(((c2 << 6) | c3) & mask);
            }
            return buffer;
        }
    }
}
