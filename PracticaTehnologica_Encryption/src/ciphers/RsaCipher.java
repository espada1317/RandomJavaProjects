package ciphers;

import javax.crypto.*;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaCipher extends CipherClass {

    private static final String cipherName = "RSA";

    public static final Boolean isRequiredKey = true;
    private static final Boolean isRequirePrivateKey = true;
    public static final Boolean isRequiredGeneratedKey = true;

    public static final String descriptionCipher = "RSA is a short form for Rivest, Shamir, and Adleman, are the people who first publicly described it in 1977. " +
            "It is an algorithm for asymmetric cryptography which involves the use of two keys. A public key, which can be known to anybody and can be used to encrypt messages, " +
            "and verify signatures. A private key, known only to the intended user, is used to decrypt messages and create signatures. RSA is asymmetric because those who encrypt " +
            "messages or verify signatures cannot decrypt messages or create signatures. RSA algorithm involves three steps which include key generation, encryption, and decryption.";

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

    public final static int BLOCK_SIZE = 4096;

    public String[] generateKey() throws NoSuchAlgorithmException {
        String[] keyPairs = new String[2];

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(BLOCK_SIZE);
        KeyPair pair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        PrivateKey privateKey = pair.getPrivate();
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        keyPairs[0] = publicKeyString;
        keyPairs[1] = privateKeyString;

        return keyPairs;
    }

    @Override
    public String encrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher encryptionCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] decodedKey = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKeyServer = keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        encryptionCipher.init(Cipher.ENCRYPT_MODE, publicKeyServer);
        byte[] encryptedMessage = encryptionCipher.doFinal(message.trim().getBytes());

        return Base64.getEncoder().encodeToString(encryptedMessage);
    }

    @Override
    public String decrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
        byte[] encryptedBytes = Base64.getDecoder().decode(message);
        Cipher decryptionCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] decodedKey = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKeyServer = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
        decryptionCipher.init(Cipher.DECRYPT_MODE, privateKeyServer);
        byte[] decryptedMessage = decryptionCipher.doFinal(encryptedBytes);

        return bytesToString(decryptedMessage);
    }

    public static String bytesToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++ )
        {
            stringBuilder.append((char) bytes[i]);
        }
        return stringBuilder.toString();
    }
}