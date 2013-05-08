package mycrypto.symmetric;

public class TripleDesEncrypter extends SymmetricEncrypter {

    public TripleDesEncrypter() {
        this(168);
    }

    public TripleDesEncrypter(int keySize) {
        super(SymmetricEncrypter.DESede, keySize);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize != 112 && this.keySize != 168) {
            suggestion = "Keysize must be equal to 112 or 168";
        }
        return suggestion;
    }
}
