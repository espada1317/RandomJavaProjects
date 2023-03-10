import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

public class Main {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        ecKeyGen.initialize(new ECGenParameterSpec("brainpoolP384r1"));

        KeyPair ecKeyPair = ecKeyGen.generateKeyPair();
        System.out.println("What is slow?");

        Cipher iesCipher = Cipher.getInstance("ECIESwithAES");
        iesCipher.init(Cipher.ENCRYPT_MODE, ecKeyPair.getPublic());

        byte[] ciphertext = iesCipher.doFinal(com.google.common.base.Strings.repeat("owlstead", 1000).getBytes());

        iesCipher.init(Cipher.DECRYPT_MODE, ecKeyPair.getPrivate());
        byte[] plaintext = iesCipher.doFinal(ciphertext);

        System.out.println(Hex.toHexString(ciphertext));
        System.out.println(new String(plaintext));
    }
}
