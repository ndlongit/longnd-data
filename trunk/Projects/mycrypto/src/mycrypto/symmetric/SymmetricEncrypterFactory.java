package mycrypto.symmetric;

public class SymmetricEncrypterFactory {

    private static SymmetricEncrypterFactory instance = null;

    protected SymmetricEncrypterFactory() {
    }

    public static synchronized SymmetricEncrypterFactory getInstance() {
        if (instance == null) {
            instance = new SymmetricEncrypterFactory();
        }
        return instance;
    }

    public SymmetricEncrypter buildEncrypter(String algorithm, int keySize) {
        SymmetricEncrypter encrypter = null;
        if (SymmetricEncrypter.DES.equals(algorithm)) {
            encrypter = new DESEncrypter(keySize);
        } else if (SymmetricEncrypter.Blowfish.equals(algorithm)) {
            encrypter = new BlowfishEncrypter(keySize);
        } else if (SymmetricEncrypter.DESede.equals(algorithm)) {
            encrypter = new TripleDesEncrypter(keySize);
        } else if (SymmetricEncrypter.RC2.equals(algorithm)) {
            encrypter = new RC2Encrypter(keySize);
        } else if (SymmetricEncrypter.RC4.equals(algorithm)) {
            encrypter = new RC4Encrypter(keySize);
        } else if (SymmetricEncrypter.AES.equals(algorithm)) {
            encrypter = new AESEncrypter(keySize);
        } else if (SymmetricEncrypter.AESWRAP.equals(algorithm)) {
            encrypter = new AESWrapEncrypter(keySize);
        } else if (SymmetricEncrypter.DESEDEWRAP.equals(algorithm)) {
            encrypter = new DESedeWrapEncrypter(keySize);
        } else {
            throw new RuntimeException("Algorithm is not supported or unimplemented");
        }
        return encrypter;
    }
}
