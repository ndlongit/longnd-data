package mycrypto.symmetric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PBEEncrypter extends SymmetricEncrypter {

    protected String password;

    protected static final int ITERATION = 19;

    protected AlgorithmParameterSpec paramSpec;

    protected static final byte[] SALT = new byte[8];

    public PBEEncrypter(String algorithm, String password) {
        this(56, algorithm, password);
    }

    public PBEEncrypter(int keySize, String algorithm, String password) {
        super(algorithm, keySize, null);
        this.password = password;
        Random random = new Random();
        random.nextBytes(SALT);
        paramSpec = new PBEParameterSpec(SALT, ITERATION);
    }

    @Override
    public void initKey() throws Exception {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray());
        skey = SecretKeyFactory.getInstance(algorithm).generateSecret(keySpec);
    }

    @Override
    public byte[] encrypt(byte[] clearText, File keyFile) {
        try {
            initKey();
            cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
            BASE64Encoder encoder = new BASE64Encoder();
            String saltString = encoder.encode(SALT);
            String cipherTextString = encoder.encode(cipher.doFinal(clearText));
            return (saltString + cipherTextString).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void encrypt(String inputFile, String outputFile, File keyFile) {
        try {
            initKey();
            cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
            FileInputStream in = new FileInputStream(new File(inputFile));
            byte[] clearTextByte = readByte(new File(inputFile));
            BASE64Encoder encoder = new BASE64Encoder();
            String saltString = encoder.encode(SALT);
            String cipherTextString = encoder.encode(cipher.doFinal(clearTextByte));
            OutputStream cout = new FileOutputStream(outputFile);
            byte[] result = (saltString + cipherTextString).getBytes();
            cout.write(result);
            in.close();
            cout.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public byte[] decrypt(byte[] cipherByte, File keyFile) {
        try {
            String text = new String(cipherByte);
            String saltString = text.substring(0, 12);
            String cipherText = text.substring(12, text.length());
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] saltArray = decoder.decodeBuffer(saltString);
            byte[] cipherTextArray = decoder.decodeBuffer(cipherText);
            char[] passwordChar = password.toCharArray();

            // Create the PBEKeySpec with the given password
            PBEKeySpec keySpec = new PBEKeySpec(passwordChar);

            // Get a SecretFactory for PBEWithSHAAndTwofish
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(this.algorithm);
            skey = keyFactory.generateSecret(keySpec);

            // Now create a parameter spec for our salt and iterations
            PBEParameterSpec paramSpec = new PBEParameterSpec(saltArray, ITERATION);
            cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);

            // perform the actual decryption
            byte[] plainTextArray = cipher.doFinal(cipherTextArray);
            return plainTextArray;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void decrypt(String inputFile, String outputFile, File keyFile) {
        try {
            byte[] cipherByte = readByte(new File(inputFile));
            String text = new String(cipherByte);
            String saltString = text.substring(0, 12);
            String cipherText = text.substring(12, text.length());
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] saltArray = decoder.decodeBuffer(saltString);
            byte[] cipherTextArray = decoder.decodeBuffer(cipherText);
            char[] passwordChar = password.toCharArray();

            // Create the PBEKeySpec with the given password
            PBEKeySpec keySpec = new PBEKeySpec(passwordChar);

            // Get a SecretFactory for PBEWithSHAAndTwofish
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(this.algorithm);
            skey = keyFactory.generateSecret(keySpec);

            // Now create a parameter spec for our salt and iterations
            PBEParameterSpec paramSpec = new PBEParameterSpec(saltArray, ITERATION);
            cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);

            byte[] plainTextArray = cipher.doFinal(cipherTextArray);
            writeByte(new File(outputFile), plainTextArray);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize != 56) {
            suggestion = "Key size must be 56";
        }
        return suggestion;
    }
}
