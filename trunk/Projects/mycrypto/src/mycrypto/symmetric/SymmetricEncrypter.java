package mycrypto.symmetric;

import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import mycrypto.Encrypter;

public abstract class SymmetricEncrypter extends Encrypter {
    public static final String RC2 = "RC2";
    public static final String RC4 = "RC4";
    public static final String DES = "DES";
    public static final String AES = "AES";
    public static final String DESede = "DESede";
    public static final String AESWRAP = "AESWRAP";
    public static final String HmacMD5 = "HmacMD5";
    public static final String Blowfish = "Blowfish";
    public static final String DESEDEWRAP = "DESEDEWRAP";
    public static final String PBEWithMD5AndDES = "PBEWithMD5AndDES";
    public static final String PBEWITHSHA1ANDRC2_40 = "PBEWITHSHA1ANDRC2_40";
    public static final String PBEWITHSHA1ANDDESEDE = "PBEWITHSHA1ANDDESEDE";
    public static final String PBEWithMD5AndTripleDES = "PBEWithMD5AndTripleDES";

    private static final String[] algorithmAray = { DES, AES, /*
                                                               * AESWRAP, DESEDEWRAP,
                                                               * HmacMD5
                                                               * ,PBEWithMD5AndTripleDES,
                                                               */
    DESede, Blowfish, PBEWithMD5AndDES, PBEWITHSHA1ANDDESEDE, PBEWITHSHA1ANDRC2_40, RC2,
            RC4 };

    protected SecretKey skey;

    public SymmetricEncrypter() {
        super();
    }

    public SymmetricEncrypter(String algorithm, int keySize) {
        this(algorithm, keySize, "/ECB/PKCS5Padding");
    }

    public SymmetricEncrypter(String algorithm, int keySize, String modeAndPadding) {
        super(algorithm, keySize, modeAndPadding);
    }

    public void initKey() throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(this.algorithm);
        kgen.init(keySize);
        skey = kgen.generateKey();
    }

    protected void storeKey(File keyFile) {
        byte[] keyByte = skey.getEncoded();
        writeByte(keyFile, keyByte);
    }

    protected void retrieveKey(File keyFile) throws Exception {
        byte[] keyByte = readByte(keyFile);
        skey = new SecretKeySpec(keyByte, this.algorithm);
    }

    @Override
    public byte[] encrypt(byte[] clearText, File keyFile) {
        try {
            initKey();
            storeKey(keyFile);
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            return cipher.doFinal(clearText);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public byte[] decrypt(byte[] cipherText, File keyFile) {
        try {
            retrieveKey(keyFile);
            cipher.init(Cipher.DECRYPT_MODE, skey);
            return cipher.doFinal(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void encrypt(String inputFile, String outputFile, File keyFile) {
        try {
            initKey();
            storeKey(keyFile);
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            execute(inputFile, outputFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void decrypt(String inputFile, String outputFile, File keyFile) {
        try {
            retrieveKey(keyFile);
            cipher.init(Cipher.DECRYPT_MODE, skey);
            execute(inputFile, outputFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String[] getAlgorithmList() {
        return algorithmAray;
    }
}
