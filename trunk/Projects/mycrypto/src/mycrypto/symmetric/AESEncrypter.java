package mycrypto.symmetric;

public class AESEncrypter extends SymmetricEncrypter {

    public AESEncrypter() {
        this(56);
    }

    public AESEncrypter(int keySize) {
        super(SymmetricEncrypter.AES, keySize);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize != 128 && this.keySize != 192 && this.keySize != 256) {
            suggestion = "Key size must be equal to 128, 192 or 256";
        }
        return suggestion;
    }
}
