package tools;

import ciphers.*;
import constants.Constants;
import factories.*;
import keyGeneration.CardanGrilleKeyAdapter;
import keyGeneration.KeyGeneric;
import keyGeneration.RsaKeyAdapter;

import java.util.regex.Pattern;

public class BackLogic
{
    public static KeyGeneric generateKeyObject(String className, String message)
    {
        KeyGeneric keyGeneric = null;

        if (className.equals("AES"))
        {
            keyGeneric = new AesCipher();
        } else if (className.equals("DES")) {
            keyGeneric = new DesCipher();
        } else if (className.equals("3DES")) {
            keyGeneric = new TripleDesCipher();
        } else if (className.equals("CardanoGrille")) {
            keyGeneric = new CardanGrilleKeyAdapter(message);
        } else if (className.equals("RSA")) {
            keyGeneric = new RsaKeyAdapter();
        } else if (className.equals("DES_Manual")) {
            keyGeneric = new DesManual();
        }

        return keyGeneric;
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
            case "algorithms.VigenereCipher" : case "RC4" : {
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
            case "DES_Manual" : {
                return true;
            }
        }

        if (Pattern.matches(selectedREGEX, key))
        {
            result = true;
        }

        return result;
    }
}