package mycrypto.symmetric;

public class DESEncrypter extends SymmetricEncrypter {

    public DESEncrypter() {
        this(56);
    }

    public DESEncrypter(int keySize) {
        super(SymmetricEncrypter.DES, keySize);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize != 56) {
            suggestion = "Key size must be 56";
        }
        return suggestion;
    }
}
