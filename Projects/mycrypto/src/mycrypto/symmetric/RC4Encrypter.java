package mycrypto.symmetric;

public class RC4Encrypter extends SymmetricEncrypter {

    public RC4Encrypter() {
        this(128);
    }

    public RC4Encrypter(int keySize) {
        super(SymmetricEncrypter.RC4, keySize, null);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize < 40 || this.keySize > 1024) {
            suggestion = "Keysize must range between 40 and 1024 (inclusive).";
        }
        return suggestion;
    }
}
