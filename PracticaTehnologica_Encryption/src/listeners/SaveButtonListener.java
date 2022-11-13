package listeners;

import main.VisualFrame;
import memento.Memento;
import memento.State;
import memento.StateCaretaker;
import proxy.LogSaveService;
import proxy.TempSaveService;
import proxy.ThirdPartySaveState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ThirdPartySaveState logSaveService = new LogSaveService(new TempSaveService());
        logSaveService.performSaveTempOperation(VisualFrame.getInstance().getCurrentClass().getNameCipher(), VisualFrame.getInstance().getPublicKey(), VisualFrame.getInstance().getPrivateKey(), VisualFrame.getInstance().getEncryptInput(), VisualFrame.getInstance().getDecryptInput());

        StateCaretaker caretaker = new StateCaretaker();
        State tempstate = new State(StateCaretaker.maxStateID)
                .setCipherName(VisualFrame.getInstance().getCurrentClass())
                .setPublicKey(VisualFrame.getInstance().getPublicKey())
                .setPrivateKey(VisualFrame.getInstance().getPrivateKey())
                .setEncryptInput(VisualFrame.getInstance().getEncryptInput())
                .setDecryptInput(VisualFrame.getInstance().getDecryptInput());

        Memento tempMemento = tempstate.createMemento();
        caretaker.addMemento(StateCaretaker.maxStateID, tempMemento);

        //caretaker.printStoredMementoObjects();
    }
}