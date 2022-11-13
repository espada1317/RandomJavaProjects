package listeners;

import ciphers.CipherClass;
import constants.Constants;
import main.VisualFrame;
import memento.Memento;
import memento.StateCaretaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextStateButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(StateCaretaker.currentStateID < StateCaretaker.maxStateID - 1) {
            StateCaretaker.currentStateID++;
            StateCaretaker stateCaretaker = new StateCaretaker();
            Memento memento = stateCaretaker.getMemento(StateCaretaker.currentStateID);

            CipherClass cipherClass = memento.getCipherName();
            String nameCipher = cipherClass.getNameCipher();
            int cipherPosComboBox = Constants.cipherAlgorithms.indexOf(nameCipher);
            JComboBox comboBox = VisualFrame.getInstance().getSelectAlgComboBox();
            comboBox.setSelectedIndex( cipherPosComboBox );

            String publicKey = memento.getPublicKey();
            VisualFrame.getInstance().setKeyInputField(publicKey);
            String privateKey = memento.getPrivateKey();
            VisualFrame.getInstance().setPrivateKeyInputField(privateKey);

            String encryptInput = memento.getEncryptInput();
            VisualFrame.getInstance().setEncryptInput(encryptInput);
            String decryptInput = memento.getDecryptInput();
            VisualFrame.getInstance().setDecryptInput(decryptInput);
        }
    }
}
