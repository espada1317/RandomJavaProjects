import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Sender
{
    private DatagramSocket datagramSocket;
    private byte[] buffer;

    public Sender(int portSender) throws SocketException {
        this.datagramSocket = new DatagramSocket(portSender);
    }

    public void sendMessages(int portSender)
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            try{
                String messageSend = scanner.nextLine();
                String[] arg = messageSend.split(" ");
                messageSend = messageSend.substring(messageSend.indexOf(' ') + 1);
                if (arg[0].contains(".255")) {
                    messageSend = "B:" + messageSend;
                }
                else {
                    messageSend = "S:" + messageSend;
                }
                buffer = messageSend.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(arg[0]), portSender);
                datagramSocket.send(datagramPacket);
            } catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
    }
}