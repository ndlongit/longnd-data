package mycrypto.symmetric;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Test {

    private static String secret = "secret";
    private static String message = "6VSIdlBUlS19IL4mI7Re75umJWtUBRHaqI3coi+G2l+peKphQw0fuOml6hDmTL9P"
            + "ppozPO3pJdNdYb3soigl1cbeUoGMVAQYjxSDhb/7A/59ECQmfUSLCijGUB47RdMP"
            + "gMHan4fTBKf/+PxJw4hBv5iK7gitoWAdYs3QJ4D3aY+gW+p253D4dDuHZXYjCoxH"
            + "CPUtslGn/qAYgzExxBtp3Iw0v7Ow9lzaWt1275rIawIjYOdtVHMfDkLWSvilkzZu"
            + "io5p+ci7h/z2Ngqjz1PeOxuI6SSjwDImdKQzGl1zBtAOC/BWQKiNC0sv4Gr3EAdO"
            + "0+81c0Ayn8r/ZSiQ9SN0RSkE2Vo+8rtYaI6phPkmV7qR35nRoNjPBH6/YXrufQjx"
            + "L9J5IZFvCWZJeZSLk8Y02OSqD+M30g14awHR30MuCkr3JF/Re3bxVBsU7sJZHetw"
            + "NwilXbZiSiKCElLAFbag038LuhOxGg7zRQrKgwYTo2piEU3tGiDZDS/sqC9GYa4F"
            + "yEu1K+vpqTv0iwX2RA8sD+2KF0UqCSrJkJImtfkFDgkXUf33CsPzl3G9ehpqzROe"
            + "vQV+T2Pc8+fRK/7XtNrP7GBz6n4oAOvcLnz0EYdHkUJ0cPoesz+OEbJCB196kx5j"
            + "gAlFsfBJtNheB0q55WypyE/vKnyr4lIacIXtrCRKzrecZHdVgf/k4VyvArz5RNIk"
            + "LwQOvl0TQYSDf5cSx/8CRuYzt2vKzjfjzavoxlTgngHw203m5DE1Wji3U6CENdyp"
            + "hvIS0TGM/i+Wa+EvvDEKEs9HtspJJ47XIXlcOHtoQCgLJdg94O+njC675NI8OQt0"
            + "EDICvtPJk02hFTOAW8OWJIi72ams/ofxBFOKi6/u5XUFccjnVsdKNJCcCxmYw9a+"
            + "WwyZ9ZjJPlOzpRDWxFm39joydySrsPqvdQI26gstxUgfyHE2QqsMczMK+soPyqRo"
            + "gHS+ujIfcX9cW3tv8W6e4ZPNzuP04ccNUWVtYqWjAWf5VF6p9tqPzwpUWOdh0Z1y"
            + "gevuy6XGA7M/6By+17Eenw6T53/XuFnzlfeNBF68Z/rBscquJQEbvCeZCKIZ/RKL"
            + "zwIVcaSdvcEDJ5DudmjutcVyVOS/envgJr423oyv7VjrdYxF+O4TsxQKNPdEez4j"
            + "jupRReM+X/ofI/+WIhr/YYpiXrlBn5qeIjwAmTnaOp9gSMkQ7rgoPgXCEyBU51+t"
            + "Oa9qAFe9KYf9aFzNqDne8dk1A9JMO6FxBnSoyF1PaDtTzf424DLk9Ym4ZEsvUGT9"
            + "fU1IC0gxb3UpV/jiGibW5Q==";

    public static void main(String[] args) {

        try {
            // Security.addProvider(new BouncyCastleProvider());

            String hmacSha1 = hmacSha1(secret, secret);
            SecretKey key = getSecretKey(hmacSha1);
            String decryptedString = decrypt(key, message);

            System.out.println(decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String hmacSha1(String secret, String message) {

        byte[] mac = new byte[20];

        try {
            Mac sha = Mac.getInstance("HmacSHA1");
            sha.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA1"));
            mac = sha.doFinal(message.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hex(mac);
    }

    private static String hex(byte[] data) {

        StringBuilder sb = new StringBuilder();

        for (byte b : data) {
            sb.append(Character.forDigit((b & 240) >> 4, 16));
            sb.append(Character.forDigit((b & 15), 16));
        }

        return sb.toString();
    }

    private static SecretKey getSecretKey(String hmacSha1) throws Exception {

        DESedeKeySpec keyspec = new DESedeKeySpec(hmacSha1.getBytes("UTF-8"));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyfactory.generateSecret(keyspec);

        return key;
    }

    private static String decrypt(SecretKey key, String base64) throws Exception {

        byte[] encryptedString = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
                .decode(base64);

        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedString = cipher.doFinal(encryptedString);

        return new String(decryptedString);
    }
}
