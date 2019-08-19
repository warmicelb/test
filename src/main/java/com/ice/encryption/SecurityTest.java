package com.ice.encryption;

import com.sun.javafx.scene.traversal.Algorithm;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author: ice
 * @create: 2019/2/25
 **/
public class SecurityTest {

    public static void main(String[] args) throws Exception {
//        base64();
//        testMD5();
//        des();
        MD5RSASign();
    }

    /**
     * MD5RSA签名测试
     * @throws Exception
     */
    public static void MD5RSASign() throws Exception {
        String privateKeyStr = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAt5zl8ih94Sp24rKleCy" +
                " OHo7zeh4MALkytk6vt2p6ksfF6BFiAoErfn2PgITR/8d6FzSfmfeMEhuC8jFS8qsXUQ" +
                " IDAQABAkEAkdaHNb4/rZai8xDeKQOKpm74qKgw9GolA3SgUNi8HtQ9qIjAM9ovAui0z" +
                " Tv07896OtScXBdQ7GkSv+Ws5BNveQIhAP0qQqBAOv40Wu7B2ITxXFkvGp1RhXQWZdCk" +
                " dTRpq8ijAiEAuatBSvKWG/4dW3XzxKILRvDSasbMvJlPn400PeItm3sCIB8fp+s9mkM" +
                " QOrGpoS3O4nSQZuw6TnbUHhZFcadM6nKTAiBHgviA/3JlkbiI86NSRv+E8Vh6G9vbgE" +
                " k0sNRASY87ZQIhAKRMe/qpz2DfrAWQiKLEZj3wyl/RiPFARmedZ0Ja3Pz/";
        Signature md5withRSA = Signature.getInstance("MD5withRSA");
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr)));
        md5withRSA.initSign(privateKey);
        md5withRSA.update("qianzhan".getBytes());
        System.out.println(Base64.encodeBase64String(md5withRSA.sign()));
    }
    /**
     * 测试摘要MD5
     * @throws NoSuchAlgorithmException
     */
    public static void testMD5() throws NoSuchAlgorithmException {
        String origin = "我热热温柔神经佛教的搜几分圣诞节放假了sad见风使舵jfjasljfjsad12412Hi~ o(*￣▽￣*)ブ号2Hi~ o(*￣▽￣*)ブ好恐慌4恐慌kj";
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        byte[] digest = messageDigest.digest(origin.getBytes());
        String result = byteToHex(digest);
        System.out.println(digest);
        System.out.println(result);
    }

    public static String byteToHex(byte[] digest){
        StringBuilder sb = new StringBuilder();
        String temp = "";
        for (byte b : digest) {
            //byte用int表示时，高24位具有随机性，用0xFF位运算后保证其有效位只有最后的8位（byte的一个字节）
            temp = Integer.toHexString(b&0xFF);
            if(temp.length()==1){
                sb.append("0"+temp);
            }else{
                sb.append(temp);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 测试base64编码
     * @throws IOException
     */
    public static void base64() throws IOException {
        String ori = ":我饿风好大说过话的撒谎个回答客户";
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(ori.getBytes());
        System.out.println("base64编码后"+encode);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        String decode = new String(base64Decoder.decodeBuffer(encode),"utf-8");
        System.out.println("base64解码后"+decode);
    }

    /**
     * 测试des加密
     * @throws NoSuchAlgorithmException
     */
    public static void des() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {

        String ori = "分为奇偶IE见覅金额为我哦佛偈防守打法";
        //生成秘钥Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] byteKey = secretKey.getEncoded();

        //转换key
        DESKeySpec desKeySpec = new DESKeySpec(byteKey);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey1 = secretKeyFactory.generateSecret(desKeySpec);

        //加密
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey1);
        byte[] encode = cipher.doFinal(ori.getBytes());
        System.out.println("DES加密后："+new BASE64Encoder().encode(encode));

        //解密
        cipher.init(Cipher.DECRYPT_MODE,secretKey1);
        byte[] decode = cipher.doFinal(encode);
        System.out.println("DES解密后:"+new String(decode));
    }

    public static void aes(){
        String ori = "分为奇偶IE见覅金额为我哦佛偈防守打法";

    }
}
