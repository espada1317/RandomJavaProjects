import javax.mail.*;
import java.util.Properties;

public class ImapReceiver {
    public static void downloadEmails() {

        Properties properties = new Properties();
        properties.put("mail.imap.host", Main.host_imap);
        properties.put("mail.imap.port", Main.port_imap);
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");

        Session session = Session.getInstance(properties);

        try {
            Store store = session.getStore("imap");
            store.connect(Main.username, Main.password);

            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            Message[] messages = folderInbox.getMessages();

            for (int i = 0; i < messages.length; i++) {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                String toList = parseAddresses(msg.getRecipients(Message.RecipientType.TO));
                String ccList = parseAddresses(msg.getRecipients(Message.RecipientType.CC));
                String sentDate = msg.getSentDate().toString();

                try {
                        String[] args = Pop3Receiver.readAttachment(msg);

                    System.out.println("\n\n\nMessage #" + (i + 1) + ":");
                    System.out.println("\t From: " + from);
                    System.out.println("\t To: " + toList);
                    System.out.println("\t CC: " + ccList);
                    System.out.println("\t Subject: " + subject);
                    System.out.println("\t Sent Date: " + sentDate);
                    System.out.println("\t Attachments: " + args[1]);
                    System.out.println("\t Message:\n" + args[0]);
                    System.out.println();

                    SmtpSender.replyToMessage(msg);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }

            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: IMAP");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseAddresses(Address[] address) {
        String listAddress = "";

        if (address != null) {
            for (int i = 0; i < address.length; i++) {
                listAddress += address[i].toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }

        return listAddress;
    }
}