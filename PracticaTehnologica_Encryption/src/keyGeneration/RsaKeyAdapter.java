package keyGeneration;

import ciphers.RsaCipher;

public class RsaKeyAdapter implements KeyGeneric{

    private RsaCipher rsaCipher = new RsaCipher();

    @Override
    public String generateKey() throws Exception {
        String[] results = rsaCipher.generateKey();
        String compressedKey = results[0] + "\\\\\\" + results[1];
        return compressedKey;
    }
}
