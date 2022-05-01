import java.net.SocketException;

public class ChatAppCompanion
{
    public static void main(String[] args) throws SocketException {
        Receiver receiver = new Receiver(4322);

        Thread thread = new Thread(()->{receiver.receiveMessages();});
        thread.start();

        Sender sender = new Sender(4323);
        sender.sendMessages(receiver.getReceiverPort());
    }
}
