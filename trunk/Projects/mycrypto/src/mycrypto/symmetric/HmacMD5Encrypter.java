package mycrypto.symmetric;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class HmacMD5Encrypter /* extends SymmetricEncrypter */{

    private static final String ENCODING = "UTF-8";
    private Mac mac;
    private String algorithm;

    public HmacMD5Encrypter(int keySize) {
        try {
            this.algorithm = SymmetricEncrypter.HmacMD5;
            KeyGenerator kg = KeyGenerator.getInstance(this.algorithm);
            SecretKey sk = kg.generateKey();

            // Get instance of Mac object implementing HMAC-MD5, and
            // initialize it with the above secret key
            mac = Mac.getInstance(this.algorithm);
            mac.init(sk);

            // System.out.println(new String(result));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String encrypt(String clearText) {
        try {
            byte[] result = mac.doFinal(clearText.getBytes(ENCODING));
            return new String(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String decrypt(String cipherText) {
        String clearText = null;
        return clearText;
    }
}
