public class CaesarSubstitution extends CipherClass
{
    private static final String cipherName = "CaesarCipher";
    private final Boolean isRequiredKey = true;
    private static final Boolean isRequirePrivateKey = false;
    private final Boolean getIsRequiredGeneratedKey = false;

    public static final String descriptionCipher = "The Caesar cipher (or Caesar code) is a monoalphabetic substitution cipher, " +
            "where each letter is replaced by another letter located a little further in the alphabet (therefore shifted but always the same for given cipher message). " +
            "The shift distance is chosen by a number called the offset, which can be right (A to B) or left (B to A).";

    public String encrypt(String message, String key)
    {
        String result = "";
        int shiftPosition = Integer.parseInt(key);
        message = message.toLowerCase();

        for (char ch : message.toCharArray())
        {
            if(ch >= 'a' && ch <= 'z')
            {
                ch = (char)(ch + shiftPosition);
                if(ch > 'z')
                    ch = (char)(ch - 'z' + 'a' - 1);

                result += ch;
            } else {
                result += ch;
            }
        }

        return result;
    }

    public String decrypt(String message, String key)
    {
        String result = "";
        int shiftPosition = Integer.parseInt(key);
        message = message.toLowerCase();

        for (char ch : message.toCharArray())
        {
            if(ch >= 'a' && ch <= 'z')
            {
                ch = (char)(ch - shiftPosition);
                if(ch < 'a')
                    ch = (char)(ch + 'z' - 'a' + 1);

                result += ch;
            } else {
                result += ch;
            }
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
}