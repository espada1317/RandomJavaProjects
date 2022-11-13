package keyGeneration;

import ciphers.CardanGrille;

public class CardanGrilleKeyAdapter implements KeyGeneric{

    private CardanGrille cardanGrille = new CardanGrille();
    private String textToEcnrypt;
    private final int coverageLevel = 80;

    public CardanGrilleKeyAdapter(String textToEcnrypt) {
        this.textToEcnrypt = textToEcnrypt;
    }

    @Override
    public String generateKey() throws Exception {
        return cardanGrille.generateMask(textToEcnrypt, coverageLevel);
    }
}
