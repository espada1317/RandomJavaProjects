import java.util.Scanner;

public class Main {

    // GENERAL SETTINGS FOR ACCOUNT
    final static String username = "espada.rop@gmail.com";
    final static String password = "qweasd1234009";

    // IMAP RECEIVER
    final static String host_imap = "imap.gmail.com";
    final static String port_imap = "993";

    // POP3 RECEIVER
    final static String host_pop3 = "pop.gmail.com";
    final static String port_pop3 = "995";

    // SMTP SENDER NO ATTACHMENT
    final static String host_smtp = "smtp.gmail.com";
    final static String port_smtp = "465";

    public static void menu() {
        System.out.println("\n-------------- Menu: ----------------");
        System.out.println("1. Show E-mails with IMAP");
        System.out.println("2. Show E-mails with POP3");
        System.out.println("3. Send E-mail");
        System.out.println("-------------------------------------\n\n");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            menu();

            System.out.println("Give option: ");
            String answer = scanner.nextLine();

            switch (answer.trim()) {
                case "1" : {
                    ImapReceiver.downloadEmails();
                    break;
                }
                case "2" : {
                    Pop3Receiver.downloadEmails();
                    break;
                }
                case "3" : {
                    SmtpSender.sendEmail();
                    break;
                }
            }
        }
    }
}