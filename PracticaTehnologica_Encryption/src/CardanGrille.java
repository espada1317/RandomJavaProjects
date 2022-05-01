public class CardanGrille extends CipherClass
{
    private static final String cipherName = "CardanoGrille";

    public static final Boolean isRequiredKey = true;
    public static final Boolean isRequiredGeneratedKey = true;
    private static final Boolean isRequirePrivateKey = false;

    public static final String descriptionCipher = "A Cardan grille is made from a sheet of fairly rigid paper or parchment, or from thin metal. " +
            " The paper is ruled to represent lines of handwriting and rectangular areas are cut out at arbitrary intervals between these lines. " +
            "An encipherer places the grille on a sheet of paper and writes his message in the rectangular apertures, some of which might allow a single letter, a syllable, or a whole word. " +
            "Then, removing the grille, the fragments are filled out to create a note or letter that disguises the true message. " +
            "Cardano suggested drafting the text three times in order to smooth any irregularities that might indicate the hidden words.";

    public static String generateMask(String message, int maskCoverLevel)
    {
       String result = "";
       int generateLength = Math.round( ( 100 * message.length() )/( 100 - maskCoverLevel ) );
       String[] messageSplit = message.toLowerCase().split("");

       int tempLenght = generateLength;
       for(String x: messageSplit)
       {
           int pos = (int) ((Math.random() * tempLenght) / 2);
           for(int i = 0; i < pos; i++)
           {
                result += "X";
           }
           result += "_";
           tempLenght -= pos;
       }
       return result;
    }

    public String encrypt(String message, String key)
    {
        String result = "";
        String mask = key;
        String[] messageSplit = message.toLowerCase().split("");
        String[] maskSplit = mask.split("");

        int i = 0;
        for(String x: maskSplit)
        {
            if (x.equals("X"))
            {
                int letter = (int) ((Math.random() * ( 90 - 65)) + 65);
                result += Character.toString( (char) letter );
            }
            else if (x.equals("_"))
            {
                result += messageSplit[i].toUpperCase();
                i++;
            }
        }
        return result;
    }

    public String decrypt(String message, String key)
    {
        String result = "";
        String mask = key;
        String[] messageSplit = message.toLowerCase().split("");
        String[] maskSplit = mask.split("");

        for (int i = 0; i < maskSplit.length; i++)
        {
            if (maskSplit[i].equals("_"))
            {
                result += messageSplit[i];
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