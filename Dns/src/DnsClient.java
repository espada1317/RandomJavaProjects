import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DnsClient
{
    private static final String validRange = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
    private static final String IP_REGEX = validRange + "\\." + validRange + "\\." + validRange + "\\." + validRange;

    public static void main(String[] args) throws UnknownHostException {
        Scanner scanner = new Scanner(System.in);
        InetAddress inetAddress;

        System.out.println("Command: ");

        while(true)
        {
            String command = scanner.nextLine();
            if(command.equals("exit")){
                return;
            }

            if(command.contains("resolve"))
            {
                String[] commandSplit = command.split(" ");
                if(commandSplit.length == 1)
                {
                    inetAddress = InetAddress.getLocalHost();
                    displayInfoByName(inetAddress);
                    continue;
                }

                if(Pattern.matches(IP_REGEX,commandSplit[1]))
                {
                    String[] bytes = commandSplit[1].split("\\.");
                    Integer byte0 = new Integer( Integer.parseInt(bytes[0]) );
                    Integer byte1 = new Integer( Integer.parseInt(bytes[1]) );
                    Integer byte2 = new Integer( Integer.parseInt(bytes[2]) );
                    Integer byte3 = new Integer( Integer.parseInt(bytes[3]) );
                    byte address[] = {byte0.byteValue(), byte1.byteValue(), byte2.byteValue(), byte3.byteValue()};

                    try {
                        inetAddress = InetAddress.getByAddress(address);
                        displayInfoByAddress(inetAddress);
                    } catch (UnknownHostException e)
                    {
                        System.out.println("\nInvalid ip address!\n");
                    }
                }
                else
                {
                    try {
                        inetAddress = InetAddress.getByName(commandSplit[1]);
                        displayInfoByName(inetAddress);
                    } catch (UnknownHostException e)
                    {
                        System.out.println("Invalid domain!\n");
                    }
                }
            }

            if(command.contains("use dns"))
            {
                String[] commandSplit = command.split(" ");

                if(commandSplit.length == 3)
                {
                    System.setProperty("sun.net.spi.nameservice.nameservers", commandSplit[2]);
                    System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");
                    System.out.println("Changed DNS server to: " + commandSplit[2] + "\n");
                } else {
                    System.out.println("Invalid DNS!\n");
                }
            }
        }
    }

    private static void displayInfoByName(InetAddress inetAddress)
    {
        System.out.println("Host: " + inetAddress.getHostName() + "\nAddress: " + inetAddress.getHostAddress() + "\n");
    }

    private static void displayInfoByAddress(InetAddress inetAddress)
    {
        System.out.println("Address: " + inetAddress.getHostAddress() + "\nHost name: " + inetAddress.getHostName()
                + "\nCanonical name: " + inetAddress.getCanonicalHostName() + "\n");
    }
}