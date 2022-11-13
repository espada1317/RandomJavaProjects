package ciphers;

import keyGeneration.KeyGeneric;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AesCipher extends CipherClass implements KeyGeneric {
    private static final String cipherName = "AES";

    public static final Boolean isRequiredKey = true;
    private static final Boolean isRequirePrivateKey = false;
    public static final Boolean isRequiredGeneratedKey = true;

    public static final String descriptionCipher = "AES is a 128-bit symmetric block ciphertext. This algorithm uses substitution and permutations; known as the SP networks. "
            + " It consists of multiple texts to produce a ciphertext. AES performs its calculations in the form of byte data instead of bit data."
            + " In this method, using a key generator, we will generate one key. We will get the key generator instance of AES and assign it to keyGenerator. " +
            "We are then required to initialize the key size. The key sizes values can be 128, 192 or 256 bytes.";

    private final int KEY_SIZE = 128;
    private final int DATA_LENGTH = 128;
    private Cipher encryptionCipher;

    public String generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        SecretKey key = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public String encrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] dataInBytes = message.getBytes();

        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, originalKey);
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] dataInBytes = decode(message);

        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, originalKey, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    @Override
    public String getDescription() {
        return descriptionCipher;
    }

    @Override
    public Boolean getRequireKey() {
        return isRequiredKey;
    }

    @Override
    public Boolean getRequireGeneratedKey() {
        return isRequiredGeneratedKey;
    }

    @Override
    public Boolean getRequirePrivateKey() {
        return isRequirePrivateKey;
    }

    @Override
    public String getNameCipher() {
        return cipherName;
    }
}