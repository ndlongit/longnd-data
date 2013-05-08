package mycrypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

public abstract class Encrypter {

    public static final String ENCODING = "UTF-8";

    public abstract void initKey() throws Exception;

    public abstract byte[] encrypt(byte[] clearText, File keyFile);

    public abstract byte[] decrypt(byte[] cipherText, File keyFile);

    public abstract void encrypt(String inputFile, String outputFile, File secretKey);

    public abstract void decrypt(String inputFile, String outputFile, File secretKey);

    public abstract String getKeySizeSuggestion();

    protected String algorithm;
    protected int keySize;
    protected Cipher cipher;
    protected Encrypter() {
    }
    
    protected Encrypter(String algorithm, int keySize, String modeAndPadding) {
        this.algorithm = algorithm;
        this.keySize = keySize;
        try {
            if (modeAndPadding != null) {
                cipher = Cipher.getInstance(this.algorithm + modeAndPadding);
            } else {
                cipher = Cipher.getInstance(this.algorithm);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
    }

    public static boolean writeByte(File outputFile, byte[] b) {
        boolean successful = true;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputFile);
            fos.write(b);
            fos.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            successful = false;
        }
        return successful;
    }

    public static byte[] readByte(File inputFile) throws Exception {
        FileInputStream fis = null;
        int c = -1;
        byte[] b = null;
        int fSize = -1;

        if (!inputFile.exists()) {
            throw new Exception("File Not Found");
        }

        fSize = (int) inputFile.length();

        try {
            fis = new FileInputStream(inputFile);
            int avai = fis.available();

            if (fSize == avai) {
                b = new byte[fSize];
                for (int i = 0; (c = fis.read()) != -1; i++) {
                    b[i] = (byte) c;
                }
            }
            fis.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return b;
    }

    protected void execute(String inputFile, String outputFile) throws Exception {
        OutputStream out = new CipherOutputStream(new FileOutputStream(outputFile),
                cipher);
        InputStream in = new FileInputStream(inputFile);
        int numRead = 0;
        byte[] buf = new byte[100];
        while ((numRead = in.read(buf)) > 0) {
            out.write(buf, 0, numRead);
        }
        out.flush();
        out.close();
    }

    public Cipher getCipher() {
        return this.cipher;
    }

    @Override
    public String toString() {
        return this.algorithm;
    }
}
