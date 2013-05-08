package mycrypto.asymmetric;

public class RSAEncrypter extends AsymmetricEncrypter {

    public RSAEncrypter() {
        this(1024);
    }

    public RSAEncrypter(int keySize) {
        super(AsymmetricEncrypter.RSA, keySize);
    }

    @Override
    public String getKeySizeSuggestion() {
        String suggestion = null;
        if (this.keySize < 512 || this.keySize > 65536) {
            suggestion = "Keysize must range between 512 and 65536.";
        }
        return suggestion;
    }

}
