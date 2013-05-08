package mycrypto.symmetric;

public class RC2Encrypter extends SymmetricEncrypter {

    public RC2Encrypter() {
        this(128);
    }

    public RC2Encrypter(int keySize) {
        super(SymmetricEncrypter.RC2, keySize, null);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize < 40 || this.keySize > 1024) {
            suggestion = "Keysize must range between 40 and 1024 (inclusive).";
        }
        return suggestion;
    }
}
