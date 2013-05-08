package mycrypto.symmetric;

public class DESedeWrapEncrypter extends SymmetricEncrypter {

    public DESedeWrapEncrypter() {
        this(56);
    }

    public DESedeWrapEncrypter(int keySize) {
        super(SymmetricEncrypter.DESEDEWRAP, keySize, null);
    }

    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize != 56) {
            suggestion = "Key size must be 56";
        }
        return suggestion;
    }
}
