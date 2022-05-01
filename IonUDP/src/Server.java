import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private DatagramSocket socket;
    private byte[] buf = new byte[256];
    public InetAddress senderAdress;
    public int senderPort;

    public Server()
    {
        try
        {
            socket=new DatagramSocket(4455);
            //socket.setSoTimeout(300);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public Server(int port)
    {
        try
        {
            socket=new DatagramSocket(port);
            //socket.setSoTimeout(300);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String listen()
    {
        String received=null;
        try
        {
            DatagramPacket packet= new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            senderAdress=packet.getAddress();
            senderPort=packet.getPort();
            received= new String(packet.getData(), 0, packet.getLength());

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return received.trim();
    }
    protected void finalize()
    {
        if(socket!=null && !socket.isClosed())
        {
            socket.close();
        }
    }

}