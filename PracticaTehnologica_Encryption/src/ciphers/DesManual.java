package ciphers;

import constants.Constants;
import keyGeneration.KeyGeneric;
import main.VisualFrame;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DesManual extends CipherClass implements KeyGeneric {

    private static final String cipherName = "DES_Manual";

    public static final Boolean isRequiredKey = true;
    private static final Boolean isRequirePrivateKey = false;
    public static final Boolean isRequiredGeneratedKey = true;

    public static final String descriptionCipher = "The Data Encryption Standard is a symmetric-key algorithm for the encryption of digital data. " +
            " Although its short key length of 56 bits makes it too insecure for applications, it has been highly influential in the advancement of cryptography." +
            " DES is insecure due to the relatively short 56-bit key size. In January 1999, distributed.net and the Electronic Frontier Foundation collaborated to publicly break a DES key in " +
            "22 hours and 15 minutes (see chronology). There are also some analytical results which demonstrate theoretical weaknesses in the cipher, although they are infeasible in practice. " +
            " The algorithm is believed to be practically secure in the form of Triple DES, although there are theoretical attacks. This cipher has been superseded by the Advanced Encryption Standard (AES). ";

    private final int sizeOfBlock = 64;
    private final int sizeOfChar = 8;
    private final int quantityOfRounds = 16;
    String[] Blocks;

    int[] PC1
            = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44,
            36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };

    int[] PC2
            = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37,
            47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };

    int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4,  62, 54, 46, 38, 30, 22,
            14, 6,  64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9,  1,  59, 51, 43, 35, 27,
            19, 11, 3,  61, 53, 45, 37, 29, 21, 13, 5,  63, 55, 47, 39, 31, 23, 15, 7 };

    int[] IP1 = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22,
            62, 30, 37, 5,  45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3,  43, 11,
            51, 19, 59, 27, 34, 2,  42, 10, 50, 18, 58, 26, 33, 1,  41, 9, 49, 17, 57, 25 };

    int EXPANSION_TABLE[] = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

    public int PERMUTATION_TABLE[] = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};

    int SBOX[][][] = {
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            },
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            },
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            },
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            },
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            },
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            },
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            }
    };

    int[] shiftBits = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };

    public String BringMessageToRightLength(String message) {
        while (((message.length() * sizeOfChar) % sizeOfBlock) != 0) {
            message += "#";
        }
        return message;
    }

    public String StringToBinaryFormat(String message) {
        String output = "";

        for (int i = 0; i < message.length(); i++) {
            String char_binary = Integer.toBinaryString(message.charAt(i));
            while (char_binary.length() < sizeOfChar) {
                char_binary = "0" + char_binary;
            }
            output += char_binary;
        }
        return output;
    }

    public void DivideBinaryStringIntoBlocks(String input) {
        Blocks = new String[input.length() / sizeOfBlock];
        for (int i = 0; i < Blocks.length; i++) {
            Blocks[i] = input.substring(i * sizeOfBlock, i * sizeOfBlock + sizeOfBlock);
        }
    }

    public String CorrectKeyWord(String key, int lengthKey) {
        if (key.length() > lengthKey)
            key = key.substring(0, lengthKey);
        else
            while (key.length() < lengthKey)
                key = "0" + key;

        return key;
    }

    public String EncodeOneRound(String input, String key) {
        String L = input.substring(0, input.length() / 2);
        String R = input.substring(input.length() / 2);

//        System.out.println("L = " + L + "  R = " + R);

        return (R + XOR(L, f(R, key)));
    }

    public String DecodeOneRound(String input, String key) {
        String L = input.substring(0, input.length() / 2);
        String R = input.substring(input.length() / 2);

//        System.out.println("L = " + L + "  R = " + R);

        return (XOR(f(L, key), R) + L);
    }

    public String XOR(String s1, String s2) {
        String result = "";
        boolean a;
        boolean b;

        for (int i = 0; i < s1.length(); i++) {
            a = ReturnBooleanValueFromString(s1.charAt(i));
            b = ReturnBooleanValueFromString(s2.charAt(i));

            if (a ^ b)
                result += "1";
            else
                result += "0";
        }
        return result;
    }

    public boolean ReturnBooleanValueFromString(char temp) {
        if (temp == '1')
            return true;
        else
            return false;
    }

    public String PermutationText(String temp, int[] table) {
        String result = "";

        for (int var : table) {
            result += temp.charAt(var - 1);
        }

        return result;
    }

    public List<String> SplitToSixBits(String temp) {
        List<String> tempList = new ArrayList<>();

        for (int i = 0; i < temp.length(); i += 6) {
            String part = temp.substring(i, Math.min(temp.length(), i + 6));
            tempList.add(part);
        }

        return tempList;
    }

    public String GetFirstAndLastBit(String temp) {
        return (String.valueOf(temp.charAt(0)) + temp.charAt(5));
    }

    public String GetMidBits(String temp) {
        return temp.substring(1, 5);
    }

    public int BinaryStringToDecimal(String temp) {
        return Integer.parseInt(temp, 2);
    }

    public String DecimalToBinaryString(int temp) {
        String result = Integer.toBinaryString(temp);

        while (result.length() < 4) {
            result = "0" + result;
        }

        return result;
    }

    public String SboxLookup(int SboxTable, String firstLastBits, String middleBits) {
        int firstLast = BinaryStringToDecimal(firstLastBits);
        int middle = BinaryStringToDecimal(middleBits);
        int sboxValue = SBOX[SboxTable][firstLast][middle];

        return DecimalToBinaryString(sboxValue);
    }

    public String f(String messageHalf, String key) {
        String expandedHalf = PermutationText(messageHalf, EXPANSION_TABLE);
        String tempResult = XOR(expandedHalf, key);
        List<String> sixBits = SplitToSixBits(tempResult);

        int i = 0;
        for (String x : sixBits) {
            tempResult += SboxLookup(i, GetFirstAndLastBit(x), GetMidBits(x));
            i++;
        }

        String result = PermutationText(tempResult, PERMUTATION_TABLE);

        return result;
    }

    public String KeyToNextRound(String key, int shiftKey) {
        for (int i = 0; i < shiftKey; i++) {
            key = key + key.charAt(0);
            key = key.substring(1);
        }

        return key;
    }

    public String[] GetSubkeys(String key) {
        String[] result = new String[16];

        key = PermutationText(key, PC1);

        for (int i = 0; i < 16; i++) {
            key = KeyToNextRound(key.substring(0, 28), shiftBits[i]) + "" + KeyToNextRound( key.substring(28, 56), shiftBits[i]);
            result[i] = PermutationText(key, PC2);
        }

        return result;
    }

    public String BinaryStringToPlainText(String input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i += sizeOfChar) {
            String part = input.substring(i, Math.min(input.length(), i + sizeOfChar));
            int val = Integer.parseInt(part, 2);
            String c = Character.toString((char) val);
            sb.append(c);
        }

        return sb.toString();
    }

    public String generateKey() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static boolean OnlyDigits(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '1') {
//                System.out.println("shit char > [" + str.charAt(i) + "]");
                return false;
            }
        }
        return true;
    }

    @Override
    public String encrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String result = "";
        String initial_message = message;
        if (!OnlyDigits(initial_message))
        {
            message = BringMessageToRightLength(message);
//            System.out.println("jopa Initial Message: " + message);
            message = StringToBinaryFormat(message);
        }

//        System.out.println("Initial Message: " + message);
        DivideBinaryStringIntoBlocks(message);

//        System.out.println("Encryption process");
        key = CorrectKeyWord(key, sizeOfChar);
        VisualFrame.getInstance().setKeyInputField(key);
//        System.out.println("Corrected key length: " + key);

        key = StringToBinaryFormat(key);
//        System.out.println("Binary key: " + key);

        String[] subKeys = GetSubkeys(key);

        for(int j = 0; j < Blocks.length; j++) {
            Blocks[j] = PermutationText(Blocks[j], IP);
        }

        for (int j = 0; j < quantityOfRounds; j++) {
//            System.out.println("Round " + (j + 1));
//            System.out.println("Key after shifting << " + subKeys[j]);
            for (int i = 0; i < Blocks.length; i++) {
                Blocks[i] = EncodeOneRound(Blocks[i], subKeys[j]);
//                System.out.println("Block " + (i+1) + " : " + Blocks[i]);
            }
        }

        for (int i = 0; i < Blocks.length; i++) {
//            String temp = Blocks[i];
//            Blocks[i] = temp.substring(temp.length()/2) + temp.substring(0, temp.length()/2);
            Blocks[i] = PermutationText(Blocks[i], IP1);
            result += Blocks[i];
        }

        if (OnlyDigits(initial_message))
        {
//            System.out.println(result);
            return result;
        }

        return BinaryStringToPlainText(result);
    }

    @Override
    public String decrypt(String message, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {

        String initial_message = message;
//        System.out.println("Decryption process");
//        System.out.println("Key: " + key);
        key = StringToBinaryFormat(key);
//        System.out.println("Binary key: " + key);

        String[] subKeys = GetSubkeys(key);

        if (!OnlyDigits(initial_message))
        {
            message = StringToBinaryFormat(message);
        }

        DivideBinaryStringIntoBlocks(message);

        for(int j = 0; j < Blocks.length; j++) {
            Blocks[j] = PermutationText(Blocks[j], IP);
        }

        for (int j = 15; j >= 0; j--) {
//            System.out.println("Round " + (j + 1));
//            System.out.println("Key after shifting << " + subKeys[j]);
            for (int i = 0; i < Blocks.length; i++) {
                Blocks[i] = DecodeOneRound(Blocks[i], subKeys[j]);
//                System.out.println("Block " + (i+1) + " : " + Blocks[i]);
            }
        }

        String result = "";

        for (int i = 0; i < Blocks.length; i++) {
//            String temp = Blocks[i];
//            Blocks[i] = temp.substring(temp.length()/2) + temp.substring(0, temp.length()/2);
            Blocks[i] = PermutationText(Blocks[i], IP1);
            result += Blocks[i];
        }

        if (OnlyDigits(initial_message))
        {
//            System.out.println(result);
            return result;
        }

        return BinaryStringToPlainText(result);
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