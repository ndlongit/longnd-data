package mycrypto.asymmetric;

public class AsymmetricEncrypterFactory {

    private static AsymmetricEncrypterFactory instance = null;

    private AsymmetricEncrypterFactory() {
    }

    public static synchronized AsymmetricEncrypterFactory getInstance() {
        if (instance == null) {
            instance = new AsymmetricEncrypterFactory();
        }
        return instance;
    }

    public AsymmetricEncrypter buildEncrypter(String algorithm, int keySize) {
        AsymmetricEncrypter encrypter = null;
        if (AsymmetricEncrypter.DSA.equals(algorithm)) {
            encrypter = new DSAEncrypter(keySize);
        } else if (AsymmetricEncrypter.Diffie_Hellman.equals(algorithm)) {
            encrypter = new DiffieHellmanEncrypter(keySize);
        } else if (AsymmetricEncrypter.RSA.equals(algorithm)) {
            encrypter = new RSAEncrypter(keySize);
        } else {
            throw new RuntimeException("Algorithm is not supported or unimplemented");
        }
        return encrypter;
    }
}
