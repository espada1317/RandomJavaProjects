import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Receiver
{
    private DatagramSocket datagramSocket;
    private int receiverPort;
    private byte[] buffer = new byte[1024];

    public Receiver(int receiverPort) throws SocketException {
        this.receiverPort = receiverPort;
        this.datagramSocket = new DatagramSocket(receiverPort);
    }

    public int getReceiverPort() {
        return receiverPort;
    }

    public void receiveMessages()
    {
        while(true)
        {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress = datagramPacket.getAddress();
                String messageFromSender = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                if (messageFromSender.contains("B:")) {
                    System.out.println("Public message from " + inetAddress.toString() + " > " + messageFromSender.trim().substring(2));
                }
                else {
                    System.out.println("Secret message from  " + inetAddress.toString() + " > " + messageFromSender.trim().substring(2));
                }
            } catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
