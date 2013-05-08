// [LICENCE-HEADER]
//
package vn.pyco.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public final class HashHelper {
    public static final String md5Hash(byte[] data) {
        return hash("MD5", data);
    }
    
    public static final String sha1Hash(byte[] data) {
        return hash("SHA-1", data);
    }
    
    private static final String hash(String algorithm, byte[] data) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        messageDigest.update(data);
        byte[] result = messageDigest.digest();
        
        return StringHelper.toHexString(result);
    }
}
