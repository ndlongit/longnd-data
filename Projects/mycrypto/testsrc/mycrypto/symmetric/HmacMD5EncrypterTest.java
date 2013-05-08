package mycrypto.symmetric;

import junit.framework.TestCase;

public class HmacMD5EncrypterTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCase01() {
        HmacMD5Encrypter encrypter = new HmacMD5Encrypter(8);
        String cipherText = encrypter.encrypt("Nguyễn Đình Long");
        System.out.println(cipherText);
    }
}
