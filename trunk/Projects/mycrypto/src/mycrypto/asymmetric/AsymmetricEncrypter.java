package mycrypto.asymmetric;

import java.io.File;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import mycrypto.Encrypter;

public abstract class AsymmetricEncrypter extends Encrypter {

    public static final int MAX_BLOCK_SIZE = 117;

    public static final String Diffie_Hellman = "DiffieHellman";
    public static final String DSA = "DSA";
    public static final String RSA = "RSA";

    private static final String[] algorithmAray = { /*Diffie_Hellman, DSA,*/ RSA };

    protected PublicKey pubKey;
    protected PrivateKey priKey;
    protected String publicKeyFileName;

    public AsymmetricEncrypter() {
        super();
    }

    public AsymmetricEncrypter(String algorithm, int keySize) {
        this(algorithm, keySize, "/ECB/PKCS1Padding");
    }

    public AsymmetricEncrypter(String algorithm, int keySize, String modeAndPadding) {
        super(algorithm, keySize, modeAndPadding);
    }

    public void initKey() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(this.algorithm);
        kpg.initialize(keySize);
        KeyPair keyPair = kpg.generateKeyPair();
        priKey = keyPair.getPrivate();
        pubKey = keyPair.getPublic();
    }

    protected void storePriKey(File keyFile) {
        byte[] priKeyByte = priKey.getEncoded();
        writeByte(keyFile, priKeyByte);
    }
    // Hoa add
    // Date 2009/07/13
    protected void storePubllicKey(File keyFile) {
        byte[] pbKeyByte = pubKey.getEncoded();
        writeByte(keyFile, pbKeyByte);
    }
    //End

    protected void retrievePriKey(File keyFile) throws Exception {
        byte[] priKeyByte = readByte(keyFile);
        KeyFactory keyFac = KeyFactory.getInstance(this.algorithm);
        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(priKeyByte);
        priKey = keyFac.generatePrivate(encodedKeySpec);
    }

    protected void retrievePubKey(File keyFile) throws Exception {
        byte[] pubKeyByte = readByte(keyFile);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKeyByte);
        KeyFactory keyFact = KeyFactory.getInstance(this.algorithm);
        pubKey = keyFact.generatePublic(x509KeySpec);
    }

    @Override
    public byte[] encrypt(byte[] clearText, File keyFile) {
        try {
            initKey();
            storePriKey(keyFile);
            storePubllicKey(new File(publicKeyFileName));
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(clearText);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public byte[] decrypt(byte[] cipherText, File keyFile) {
        try {
            retrievePriKey(keyFile);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            return cipher.doFinal(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void decrypt(String inputFile, String outputFile, File keyFile) {
        try {
            retrievePriKey(keyFile);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            execute(inputFile, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void encrypt(String inputFile, String outputFile, File keyFile) {
        try {
            initKey();
            storePriKey(keyFile);
            storePubllicKey(new File(publicKeyFileName));
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            execute(inputFile, outputFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String[] getAlgorithmList() {
        return algorithmAray;
    }

    /**
     * Set PublicKey file name for AsymmetricEncrypter
     * @param text
     */
    public void setKeyFile2(String fileName) {
        this.publicKeyFileName = fileName;
    }
}
