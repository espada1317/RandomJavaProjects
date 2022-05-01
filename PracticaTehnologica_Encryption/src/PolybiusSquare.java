import com.sun.org.apache.xpath.internal.operations.Bool;

public class PolybiusSquare extends CipherClass
{
    private static final String cipherName = "PolybiusSquare";

    public static final Boolean isRequiredKey = false;
    private static final Boolean isRequirePrivateKey = false;
    public static final Boolean isRequiredGeneratedKey = false;

    public static final String descriptionCipher = "The Polybius Square is an ancient Greek invention, discovered by a scholar named Polybius. " +
            "   For the Greek alphabet of 24 letters, it consisted of a 5 by 5 grid where each square of the grid was filled by a single letter. " +
            "In the English Alphabet of 26 letters, we have one too many letters. To get round this we combine two letters, traditionally i and j." +
            "It is also possible to combine other pairs, such as v and u. " +
            "Another alternative to the Polybius Square for english is to include the digits 0-9, so we have 36 characters.";

    private final static String AlphabetMatrix[][] = {
            { "A", "B", "C", "D", "E" },
            { "F", "G", "H", "I", "K" },
            { "L", "M", "N", "O", "P" },
            { "Q", "R", "S", "T", "U" },
            { "V", "W", "X", "Y", "Z" },
            { " ", ".", ",", "!", "?" }
    };

    public String encrypt(String message, String key)
    {
        String result = "";
        message = message.toLowerCase();
        for (int i = 0;i < message.length(); i++)
        {
            int row = (int)Math.ceil((message.charAt(i) - 'a') / 5) + 1;
            int col = ((message.charAt(i) - 'a') % 5) + 1;

            if (message.charAt(i) >= '0' && message.charAt(i) <= '9')
            {
                result += "0" + message.charAt(i);
                continue;
            }
            else if (message.charAt(i) == ' ')
            {
                result += "61";
                continue;
            }
            else if (message.charAt(i) == '.')
            {
                result += "62";
                continue;
            }
            else if (message.charAt(i) == ',')
            {
                result += "63";
                continue;
            }
            else if (message.charAt(i) == '!')
            {
                result += "64";
                continue;
            }
            else if (message.charAt(i) == '?')
            {
                result += "65";
                continue;
            }
            else if (message.charAt(i) < 'a' || message.charAt(i) > 'z')
            {
                continue;
            }

            if (message.charAt(i) == 'k')
            {
                row = row - 1;
                col = 5 - col + 1;
            } else if (message.charAt(i) >= 'j')
            {
                if (col == 1)
                {
                    col = 6;
                    row = row - 1;
                }
                col = col - 1;
            }
            result += (row +""+ col);
        }

        return result;
    }

    public String decrypt(String message, String key)
    {
        String result = "";
        message = message.toLowerCase();

        for (int i = 0; i < message.length(); i+=2)
        {
            String temp = message.substring(i, i+2);
            String[] pos = temp.split("");
            int row = Integer.parseInt(pos[0]) - 1;
            int col = Integer.parseInt(pos[1]) - 1;
            if (row == -1)
            {
                result += String.valueOf(col + 1);
            }
            else
                result += AlphabetMatrix[row][col];
        }

        return result.toLowerCase();
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