import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private DatagramSocket socket;

    private byte[] buf;


    public Client()
    {
        try
        {
            socket=new DatagramSocket();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public Client(int port)
    {
        try
        {
            socket=new DatagramSocket(port);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void send(String message, String address, int port)
    {
        try
        {
            buf = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(address), port);
            socket.send(packet);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void send(String message, InetAddress address, int port)
    {
        try
        {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    protected void finalize()
    {
        if(socket!=null && !socket.isClosed())
        {
            socket.close();
        }
    }
}