package memento;

import java.util.HashMap;
import java.util.Map;

public class StateCaretaker {

    public static int maxStateID = 1;
    public static int currentStateID = 1;
    static Map<Integer, Memento> mementoHistory = new HashMap<Integer, Memento>();

    public void addMemento(int stateID, Memento memento)
    {
        if (memento != null)
        {
            mementoHistory.put(stateID, memento);
        }
        maxStateID++;
        currentStateID = maxStateID;
    }

    public Memento getMemento(int stateID)
    {
        Memento memento = null;
        memento = mementoHistory.get(stateID);
        return memento;
    }

    public void printStoredMementoObjects() {
        System.out.println("======================================================");
        mementoHistory.forEach((stateID, memento) -> {
                System.out.printf("StateID: " + stateID + " , " + memento + "\n");
            });
        System.out.println("======================================================");
    }
}
