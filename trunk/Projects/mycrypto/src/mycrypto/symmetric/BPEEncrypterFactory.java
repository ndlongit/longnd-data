package mycrypto.symmetric;

public class BPEEncrypterFactory extends SymmetricEncrypterFactory {
    private static BPEEncrypterFactory instance = null;

    private BPEEncrypterFactory() {
        super();
    }

    public static synchronized BPEEncrypterFactory getInstance() {
        if (instance == null) {
            instance = new BPEEncrypterFactory();
        }
        return instance;
    }

    public SymmetricEncrypter buildEncrypter(String algorithm, int keySize,
            String password) {
        return new PBEEncrypter(keySize, algorithm, password);
    }
}
