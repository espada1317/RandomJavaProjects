import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Pop3Receiver {

    public static void downloadEmails() {
        try {
            Properties properties = new Properties();

            properties.put("mail.pop3.host", Main.host_pop3);
            properties.put("mail.pop3.port", Main.port_pop3);
            properties.put("mail.pop3.ssl.enable", "true");

            Session emailSession = Session.getInstance(properties);
            Store store = emailSession.getStore("pop3");
            store.connect(Main.username, Main.password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("Messages in Inbox > " + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];

                String toList = parseAddresses(message.getRecipients(Message.RecipientType.TO));
                String ccList = parseAddresses(message.getRecipients(Message.RecipientType.CC));

                try {
                    String[] args = readAttachment(message);

                    System.out.println("\n\n\nMessage #" + (i + 1) + ":");
                    System.out.println("\t From: " + message.getFrom()[0]);
                    System.out.println("\t To: " + toList);
                    System.out.println("\t CC: " + ccList);
                    System.out.println("\t Subject: " + message.getSubject());
                    System.out.println("\t Sent Date: " + message.getSentDate().toString());
                    System.out.println("\t Attachments: " + args[1]);
                    System.out.println("\t Message:\n" + args[0]);
                    System.out.println();

                    SmtpSender.replyToMessage(message);
                } catch (ClassCastException e) {
                    continue;
                }
            }

            emailFolder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
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

    public static String[] readAttachment(Message message) throws MessagingException, IOException {
        String attachFiles = "";
        String messageContent = "";
        String saveDirectory = "Z:/Files/";
        String contentType = message.getContentType();

        if (contentType.contains("multipart")) {
            Multipart multiPart = (Multipart) message.getContent();
            int numberOfParts = multiPart.getCount();
            for (int partCount = 0; partCount < numberOfParts; partCount++) {
                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                    String fileName = part.getFileName();
                    attachFiles += fileName + ", ";
                    part.saveFile(saveDirectory + fileName);
                } else {
                    messageContent = part.getContent().toString();
                }
            }

            if (attachFiles.length() > 1)
            {
                attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
            }
        } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
            Object content = message.getContent();
            if (content != null) {
                messageContent = content.toString();
            }
        }
        String[] args = {messageContent, attachFiles};

        return args;
    }
}