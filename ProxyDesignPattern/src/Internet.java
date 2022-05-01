import java.net.UnknownHostException;

public interface Internet {
    public void connectToHost(String host) throws UnknownHostException;
}