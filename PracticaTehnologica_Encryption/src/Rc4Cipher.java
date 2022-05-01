public class Rc4Cipher extends CipherClass {

    private static final String cipherName = "RC4";
    private final Boolean isRequiredKey = true;
    private final Boolean getIsRequiredGeneratedKey = false;
    private static final Boolean isRequirePrivateKey = false;

    public static final String descriptionCipher = "RC4 for Rivest Cipher 4 is a symmetric and fast cipher algorithm, well suited to binary data, created by Ronald Rivest and used in some protocols like TLS or WEP."
            + " Decryption is exactly the same as encryption. The codes generated by RC4 are between 0 and 255, usually represented in hexadecimal." +
            "RC4 is pseudo-random, there is no easily detectable bias. The code is also called RCfour, ARCFour, ARC4, Alleged RC4 or Ron's Code 4.";

    private static int[] S = new int[256];
    private static int[] T = new int[256];

    public static int[] stringArrayToInt(String input) {
        int[] arr = new int[input.length()];
        for (int i = 0; i < input.length(); i++)
        {
            arr[i] = input.charAt(i);
        }
        return arr;
    }

    public static void keyGeneration(int[] intKey)
    {
        int temp;
        for(int i = 0; i < 256; i++) {
            S[i] = (int) i;
            T[i] = intKey[i % intKey.length];
        }

        int j = 0;
        for(int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) % 256;
            temp = S[j];
            S[j] = S[i];
            S[i] = temp;
        }
    }

    public static String encrypt_decrypt(String messageToEncrypt, String keyString)
    {
        int temp;
        int cipher[] = new int[messageToEncrypt.length()];
        int[] messageToInt = stringArrayToInt(messageToEncrypt);
        int[] keyToInt = stringArrayToInt(keyString);

        keyGeneration(keyToInt);

        int i = 0;
        int j = 0;
        int z = 0;
        for(int l = 0; l < messageToEncrypt.length(); l++)
        {
            i = (l + 1) % 256;
            j = (j + S[i]) % 256;
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            z = S[(S[i] + S[j]) % 256];
            cipher[l] = z ^ messageToInt[l];
        }

        return display(cipher);
    }
    static String display(int disp[])
    {
        String result = "";
        char convert[] = new char[disp.length];
        for(int i = 0; i < disp.length; i++)
        {
            convert[i] = (char) disp[i];
            result += convert[i];
        }
        return result;
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
        return getIsRequiredGeneratedKey;
    }

    @Override
    public Boolean getRequirePrivateKey() {
        return isRequirePrivateKey;
    }

    @Override
    public String getNameCipher() {
        return cipherName;
    }

    @Override
    public String encrypt(String message, String key) {
        return encrypt_decrypt(message, key);
    }

    @Override
    public String decrypt(String message, String key) {
        return encrypt_decrypt(message, key);
    }
}