public class Main {
    public static void main(String[] args) {
        String protocol = "imap";
        String host = "imap.gmail.com";
        String port = "993";

        String userName = "espada.rop@gmail.com";
        String password = "qweasd1234009";

        ImapReceiver imapReceiver = new ImapReceiver();
        imapReceiver.downloadEmails(protocol, host, port, userName, password);
    }
}
