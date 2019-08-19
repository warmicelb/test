package com.ice.encryption;

import java.security.MessageDigest;
import java.security.Provider;

/**
 * @author: ice
 * @create: 2019/2/26
 **/
public class MyMessageDigest extends MessageDigest {

    protected MyMessageDigest() {
        super("ice");
    }

    @Override
    protected void engineUpdate(byte input) {

    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {

    }

    @Override
    protected byte[] engineDigest() {
        return new byte[0];
    }

    @Override
    protected void engineReset() {

    }
}
class MYProvider extends Provider{

    /**
     * Constructs a provider with the specified name, version number,
     * and information.
     *
     * @param name    the provider name.
     * @param version the provider version number.
     * @param info    a description of the provider and its services.
     */
    protected MYProvider(String name, double version, String info) {
        super("ice", 1.0, "ice security provider info");
    }
}
