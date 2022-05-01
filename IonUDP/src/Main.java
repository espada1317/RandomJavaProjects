import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static HashSet<InetAddress> set;
    private static boolean listen;

    public static void main(String args[])throws Exception
    {
        Thread t1=new Thread(()->{getMessages();});
        t1.start();
        Client client=new Client(4482);


        Scanner s=new Scanner(System.in);
        while(true)
        {
            String text=s.nextLine();
            System.out.println(text);
            if(text.equals("exit"))
                break;

            String[] messages=text.split(" ");
            String address=new String(messages[0]);
            text=text.replace(address+" ", "");
            if(text.equals("exit"))
            {
                if(set.contains(address))
                    set.remove(address);

                if(address.contains(".255"))
                {
                    set.clear();
                }
            }

            client.send(text,address,4452);

        }

        listen=false;
        t1.join();

    }

    static void getMessages()
    {
        Server server=new Server(4452);
        set=new HashSet<InetAddress>();
        listen=true;
        while(listen)
        {
            System.out.println("Your contacts:");
            for(InetAddress in:set)
            {
                System.out.println(in);
            }
            String response=server.listen();
            if(response.equals("exit"))
            {
                if(set.contains(server.senderAdress))
                    set.remove(server.senderAdress);
            }
            else
            {
                if(response.split(" ")[0].equals("broadcast"))
                    System.out.println(server.senderAdress+" sent in public Chat:"+response);
                else
                    System.out.println(server.senderAdress+" sent:"+response);
                set.add(server.senderAdress);
            }
        }
    }
}