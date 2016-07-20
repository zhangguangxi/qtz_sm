package com.mall.core.common.encryption;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA256Encryption {
	
	public static String encodeToSHA256(String text) {
        ByteArrayInputStream is = new ByteArrayInputStream(text.getBytes());
        String output;
        int read;
        byte[] buffer = new byte[8192];
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] hash = digest.digest();
            BigInteger bigInt = new BigInteger(1, hash);
            output = bigInt.toString(16);
            while (output.length() < 32) {
                output = "0" + output;
            }
        } catch (Exception e) {
            return null;
        }
        return output;
    }
public static void main(String[] args) {
	String encodeToSHA256 = encodeToSHA256("123456");
	System.out.println(encodeToSHA256);
}

}
