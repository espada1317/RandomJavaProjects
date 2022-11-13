package memento;

import ciphers.CipherClass;

public class Memento {
    private int stateID;
    private CipherClass cipherName;
    private String publicKey;
    private String privateKey;
    private String encryptInput;
    private String decryptInput;

    public int getStateID() {
        return stateID;
    }

    public CipherClass getCipherName() {
        return cipherName;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getEncryptInput() {
        return encryptInput;
    }

    public String getDecryptInput() {
        return decryptInput;
    }

    public Memento(int stateID, CipherClass cipherName, String publicKey, String privateKey, String encryptInput, String decryptInput) {
        super();
        this.stateID = stateID;
        this.cipherName = cipherName;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.encryptInput = encryptInput;
        this.decryptInput = decryptInput;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "stateID=" + stateID +
                ", cipherName=" + cipherName.getNameCipher() +
                ", publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", encryptInput='" + encryptInput + '\'' +
                ", decryptInput='" + decryptInput + '\'' +
                '}';
    }
}
