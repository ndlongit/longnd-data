package mycrypto.asymmetric;

import java.util.Collections;

import junit.framework.TestCase;

public class RSAEncrypterTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCase01() {
        String clearText = "Long Nguyễn";
        System.out.println(clearText);
        RSAEncrypter encrypter = new RSAEncrypter();
        byte[] cipherText = encrypter.encrypt(clearText.getBytes(), null);
        System.out.println(new String(cipherText));

//        encrypter = new RSAEncrypter();
        byte[] decryptedText = encrypter.decrypt(cipherText, null);
        System.out.println(new String(decryptedText));
        Collections.EMPTY_LIST.get(1);
    }
}
