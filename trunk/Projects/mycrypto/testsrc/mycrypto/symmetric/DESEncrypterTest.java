package mycrypto.symmetric;

import java.io.File;

import junit.framework.TestCase;

public class DESEncrypterTest extends TestCase {

     private File keyFile = new File("C:/Documents and Settings/Administrator/My Documents/Key.txt");

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testEncript() {
        String text = "Long Nguyễn";
        System.out.println(text);
        
        byte[] clearText = text.getBytes();
        DESEncrypter encrypter = new DESEncrypter(56);
        byte[] cipherText = encrypter.encrypt(clearText, keyFile);
        System.out.println(new String(cipherText));
        cipherText = "�fZ�⮖t�0+޺p�".getBytes();
        encrypter = new DESEncrypter(56);
        byte[] decryptedText = encrypter.decrypt(cipherText, keyFile);
        System.out.println(new String(decryptedText));
    }
}
