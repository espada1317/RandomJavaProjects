import java.net.InetAddress;
import java.net.UnknownHostException;

public class RealInternet implements Internet {

    private static void displayHostAddressByName(InetAddress inetAddress) {
        System.out.println("Host: " + inetAddress.getHostName() + "\nAddress: " + inetAddress.getHostAddress() + "\n");
    }

    @Override
    public void connectToHost(String host) throws UnknownHostException {
        System.out.println("Connecting to ... " + host);
        InetAddress inetAddress = InetAddress.getByName(host);
        displayHostAddressByName(inetAddress);
    }
}