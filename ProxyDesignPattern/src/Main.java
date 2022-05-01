import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> listSitesToCheckAccess;
    static {
        listSitesToCheckAccess = new ArrayList<>();
        listSitesToCheckAccess.add("java2novice.com");
        listSitesToCheckAccess.add("adult-site.com");
        listSitesToCheckAccess.add("www.facebook.com");
        listSitesToCheckAccess.add("www.wikipedia.com");
    }

    public static void main(String[] args) {
        Internet intConn = new ProxyInternet();

        for(String host: listSitesToCheckAccess)
        {
            try {
                intConn.connectToHost(host);
            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}