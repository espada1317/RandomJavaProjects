package factories;

import ciphers.*;

public class CipherFactory {
    public static CipherClass createCipher(String ciperName) {
        CipherClass cipherClass = null;

        if(ciperName.equals("CaesarCipher"))
        {
            cipherClass = new CaesarSubstitution();
        } else if (ciperName.equals("CardanoGrille"))
        {
            cipherClass = new CardanGrille();
        } else if (ciperName.equals("PolybiusSquare"))
        {
            cipherClass = new PolybiusSquare();
        } else if (ciperName.equals("AveMaria"))
        {
            cipherClass = new TrithemiusAveMaria();
        } else if (ciperName.equals("VigenereCipher"))
        {
            cipherClass = new VigenereCipher();
        } else if (ciperName.equals("RC4"))
        {
            cipherClass = new Rc4Cipher();
        } else if (ciperName.equals("AES"))
        {
            cipherClass = new AesCipher();
        } else if (ciperName.equals("DES"))
        {
            cipherClass = new DesCipher();
        } else if (ciperName.equals("3DES"))
        {
            cipherClass = new TripleDesCipher();
        } else if (ciperName.equals("RSA"))
        {
            cipherClass = new RsaCipher();
        } else if (ciperName.equals("DES_Manual")) {
            cipherClass = new DesManual();
        }

        return cipherClass;
    }
}