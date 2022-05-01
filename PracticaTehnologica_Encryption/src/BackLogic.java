import java.util.regex.Pattern;

public class BackLogic
{
    public static CipherClass choseObject(String message)
    {
        CipherClass cipherClass = null;

        if(message.equals("Caesar Cipher"))
        {
            cipherClass = new CaesarSubstitution();
        } else if (message.equals("Cardano Grille"))
        {
            cipherClass = new CardanGrille();
        } else if (message.equals("Polybius Square"))
        {
            cipherClass = new PolybiusSquare();
        } else if (message.equals("Trithemius Ave Maria"))
        {
            cipherClass = new TrithemiusAveMaria();
        } else if (message.equals("Vigenere Cipher"))
        {
            cipherClass = new VigenereCipher();
        } else if (message.equals("RC4"))
        {
            cipherClass = new Rc4Cipher();
        } else if (message.equals("AES"))
        {
            cipherClass = new AesCipher();
        } else if (message.equals("DES"))
        {
            cipherClass = new DesCipher();
        } else if (message.equals("3DES"))
        {
            cipherClass = new TripleDesCipher();
        } else if (message.equals("RSA"))
        {
            cipherClass = new RsaCipher();
        }

        return cipherClass;
    }

    public static String parseTextAreaDescription(String message)
    {
        String result           = "";
        int totalLines          = message.length() / Constants.JTEXTAREA_COLS;
        int currentLine         = 0;

        for(int i = 0; i < totalLines; i++)
        {
            result += message.substring(currentLine, currentLine + Constants.JTEXTAREA_COLS) + "\n";
            currentLine += Constants.JTEXTAREA_COLS;
        }

        result += message.substring( totalLines * Constants.JTEXTAREA_COLS );

        return result;
    }

    public static Boolean checkForREGEX(CipherClass cipherClass, String key)
    {
        Boolean result = false;
        String selectedREGEX = "";

        switch (cipherClass.getNameCipher())
        {
            case "CaesarCipher" : {
                selectedREGEX = Constants.CIPHER_CAESAR_REGEX;
                break;
            }
            case "CardanoGrille" : {
                selectedREGEX = Constants.CIPHER_CARDAN_GRILLE_REGEX;
                break;
            }
            case "VigenereCipher" : case "RC4" : {
                selectedREGEX = Constants.CIPHER_TEXT_REGEX;
                break;
            }
            case "AES" : {
                selectedREGEX = Constants.CIPHER_AES_REGEX;
                break;
            }
            case "DES" : {
                selectedREGEX = Constants.CIPHER_DES_REGEX;
                break;
            }
            case "3DES" : {
                selectedREGEX = Constants.CIPHER_3DES_REGEX;
                break;
            }
            case "RSA" : {
                selectedREGEX = Constants.CIPHER_RSA_PAIR_REGEX;
                break;
            }
        }

        if (Pattern.matches(selectedREGEX, key))
        {
            result = true;
        }

        return result;
    }
}