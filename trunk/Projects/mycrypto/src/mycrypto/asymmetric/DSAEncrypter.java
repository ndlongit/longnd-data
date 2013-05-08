package mycrypto.asymmetric;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class DSAEncrypter extends AsymmetricEncrypter {

    public DSAEncrypter() {
        this(1024);
    }

    public DSAEncrypter(int keySize) {
        super();
        this.algorithm = AsymmetricEncrypter.DSA;
        this.keySize = keySize;
    }

    @Override
    public void initKey() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        keyGen.initialize(1024, random);

        KeyPair keyPair = keyGen.generateKeyPair();
        priKey = keyPair.getPrivate();
        pubKey = keyPair.getPublic();
    }

    @Override
    public void encrypt(String inputFile, String outputSignature, File keyFile) {
        try {
            initKey();
            // Hoa delete
            // Date 2009/07/13
            //storePriKey(keyFile);
            Signature dsa = Signature.getInstance(this.algorithm);
            dsa.initSign(priKey);
            byte[] readFile = readByte(new File(inputFile));
            dsa.update(readFile);
            byte[] realSig = dsa.sign();
            writeByte(new File(outputSignature), realSig);
            // Hoa add
            storePubllicKey(keyFile);
            // End
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void decrypt(String inputFile, String outputSignature, File keyFile) {
        try {
            byte[] encKey = readByte(keyFile);

            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

            KeyFactory keyFactory = KeyFactory.getInstance(this.algorithm,"SUN");
            PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

            /* input the signature bytes */
            byte[] sigToVerify = readByte(new File(outputSignature));

            /*
             * create a Signature object and initialize it with the public key
             */
            Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
            sig.initVerify(pubKey);

            /* Update and verify the data */

            FileInputStream datafis = new FileInputStream(inputFile);
            BufferedInputStream bufin = new BufferedInputStream(datafis);

            byte[] buffer = new byte[this.keySize];
            int len;
            while (bufin.available() != 0) {
                len = bufin.read(buffer);
                sig.update(buffer, 0, len);
            }

            bufin.close();

            boolean verifies = sig.verify(sigToVerify);
            
            System.out.println("signature verifies: " + verifies);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize < 512 || this.keySize > 1024 || (this.keySize % 64 != 0)) {
            suggestion = "Keysize must be a multiple of 64, ranging from 512 to 1024 (inclusive).";
        }
        return suggestion;
    }
}
