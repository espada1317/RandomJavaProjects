import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class TripleDesCipher extends CipherClass {

    private static final String cipherName = "3DES";

    public static final Boolean isRequiredKey = true;
    private static final Boolean isRequirePrivateKey = false;
    public static final Boolean isRequiredGeneratedKey = true;

    public static final String descriptionCipher = "Triple DES (3DES or TDES), officially the Triple Data Encryption Algorithm (TDEA or Triple DEA), is a symmetric-key block cipher, " +
            "which applies the DES cipher algorithm three times to each data block. The Data Encryption Standard's (DES) 56-bit key is no longer considered adequate in the face of " +
            "modern cryptanalytic techniques and supercomputing power. A CVE released in 2016, CVE-2016-2183 disclosed a major security vulnerability in DES and 3DES encryption " +
            "algorithms. This CVE, combined with the inadequate key size of DES and 3DES, NIST has deprecated DES and 3DES for new applications in 2017, and for all applications by 2023. " +
            "It has been replaced with the more secure, more robust AES.";

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

    public String generateKey () throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeyGenerator keyFactory = KeyGenerator.getInstance("DESede");
        SecretKey theKey = keyFactory.generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(theKey.getEncoded());
        return encodedKey;
    }

    @Override
    public String encrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec IvParameters = new IvParameterSpec(new byte[]{12, 34, 56, 78, 90, 87, 65, 43});
        cipher.init(Cipher.ENCRYPT_MODE, originalKey, IvParameters);
        byte[] encrypted = cipher.doFinal(message.getBytes());
        String result = Base64.getEncoder().encodeToString(encrypted);

        return result;
    }

    @Override
    public String decrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec ivParameters = new IvParameterSpec(new byte[]{12, 34, 56, 78, 90, 87, 65, 43});
        cipher.init(Cipher.DECRYPT_MODE, originalKey, ivParameters);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(message.getBytes()));
        String result = new String(original);

        return result;
    }
}
