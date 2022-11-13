package ciphers;

import keyGeneration.KeyGeneric;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DesCipher extends CipherClass implements KeyGeneric {

    private static final String cipherName = "DES";

    public static final Boolean isRequiredKey = true;
    private static final Boolean isRequirePrivateKey = false;
    public static final Boolean isRequiredGeneratedKey = true;

    public static final String descriptionCipher = "The Data Encryption Standard is a symmetric-key algorithm for the encryption of digital data. " +
            " Although its short key length of 56 bits makes it too insecure for applications, it has been highly influential in the advancement of cryptography." +
            " DES is insecure due to the relatively short 56-bit key size. In January 1999, distributed.net and the Electronic Frontier Foundation collaborated to publicly break a DES key in " +
            "22 hours and 15 minutes (see chronology). There are also some analytical results which demonstrate theoretical weaknesses in the cipher, although they are infeasible in practice. " +
            " The algorithm is believed to be practically secure in the form of Triple DES, although there are theoretical attacks. This cipher has been superseded by the Advanced Encryption Standard (AES). ";

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

    public String generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey key = keyGenerator.generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(key.toString());
        return encodedKey;
    }

    @Override
    public String encrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] byteMessage = message.getBytes();
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
        Cipher myCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        myCipher.init(Cipher.ENCRYPT_MODE, originalKey);
        byte[] myEncryptedMessage = myCipher.doFinal(byteMessage);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(myEncryptedMessage);

        return result;
    }

    @Override
    public String decrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] byteMessage = base64Decoder.decodeBuffer(message);
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
        Cipher myCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        myCipher.init(Cipher.DECRYPT_MODE, originalKey);
        byte[] myDecryptedMessage = myCipher.doFinal(byteMessage);
        String result = bytesToString(myDecryptedMessage);

        return result;
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