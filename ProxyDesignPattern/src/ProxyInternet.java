import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ProxyInternet implements Internet {
    private Internet internet = new RealInternet();
    private static List<String> restrictedHosts;

    static {
        restrictedHosts = new ArrayList<>();
        restrictedHosts.add("www.wikipedia.com");
        restrictedHosts.add("www.facebook.com");
        restrictedHosts.add("www.instagram.com");
    }

    @Override
    public void connectToHost(String host) throws UnknownHostException {
        if(!restrictedHosts.contains(host.toLowerCase())){
            internet.connectToHost(host);
        } else {
            throw new UnknownHostException("Connecting to ... " + host + "\nCompany restricted this site view!\n");
        }
    }
}