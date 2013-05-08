package mycrypto.symmetric;

public class BlowfishEncrypter extends SymmetricEncrypter {

    public BlowfishEncrypter() {
        this(128);
    }

    public BlowfishEncrypter(int keySize) {
        super(SymmetricEncrypter.Blowfish, keySize);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize % 8 != 0) {
            suggestion = "Keysize must be multiple of 8, and can only range from 32 to 448 (inclusive)";
        }
        return suggestion;
    }
}
