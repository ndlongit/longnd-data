package mycrypto.asymmetric;

public class DiffieHellmanEncrypter extends AsymmetricEncrypter {

    public DiffieHellmanEncrypter() {
        this(1024);
    }

    public DiffieHellmanEncrypter(int keySize) {
        super(AsymmetricEncrypter.Diffie_Hellman, keySize, null);
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
