package mycrypto.symmetric;

import java.io.File;
import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

public class PBEEncrypterTest extends TestCase {

    private static final String PBEWITH_M_D5_AND_D_E_S = SymmetricEncrypter.PBEWithMD5AndDES;
    private static final String PASSWORD = "123";
    String filePath = "C:/Documents and Settings/Administrator/My Documents/";
    File keyFile = new File(filePath + "Key.txt");
    
    private PBEEncrypter encrypter = new PBEEncrypter(56, PBEWITH_M_D5_AND_D_E_S, PASSWORD);

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void xtestCase1() {
        String inputFile = filePath + "Test.doc";
        String outputFile = filePath + "Test_E.doc";

        // String text = "Long Nguyễn";
        encrypter.encrypt(inputFile, outputFile, keyFile);

        // System.out.println(new String(result));

        encrypter = new PBEEncrypter(56, PBEWITH_M_D5_AND_D_E_S, PASSWORD);
        encrypter.decrypt(outputFile, inputFile + "_D", keyFile);
        // System.out.println(new String(encrypter.decrypt(result)));

    }
    public void testCase2() {
        String s = null;
        String s2 = null;
        try {
            s2 = new String(encrypter.encrypt("long".getBytes(), keyFile));
            s = new String(encrypter.decrypt(s2.getBytes("UTF-8"), keyFile));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
