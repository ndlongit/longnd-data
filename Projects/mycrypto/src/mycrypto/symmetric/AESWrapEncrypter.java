package mycrypto.symmetric;

public class AESWrapEncrypter extends SymmetricEncrypter {

    public AESWrapEncrypter() {
        this(56);
    }

    public AESWrapEncrypter(int keySize) {
        super(SymmetricEncrypter.AESWRAP, keySize, null);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize != 56) {
            suggestion = "Key size must be 56";
        }
        return suggestion;
    }
}
