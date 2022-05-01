import java.util.Properties;

import javax.mail.*;

public class ImapReceiver {
    private Properties getServerProperties(String protocol, String host, String port) {
        Properties properties = new Properties();

        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);

        properties.setProperty( String.format("mail.%s.socketFactory.class", protocol), "javax.net.ssl.SSLSocketFactory");
        properties.setProperty( String.format("mail.%s.socketFactory.fallback", protocol), "false");
        properties.setProperty( String.format("mail.%s.socketFactory.port", protocol), String.valueOf(port));

        return properties;
    }

    public void downloadEmails(String protocol, String host, String port, String userName, String password) {
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        try {
            Store store = session.getStore(protocol);
            store.connect(userName, password);

            Folder folderInbox = store.getFolder("Inbox");
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

                String contentType = msg.getContentType();
                String messageContent = "";

                if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    } catch (Exception ex) {
                        messageContent = "[Error downloading content]";
                        ex.printStackTrace();
                    }
                }

                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);
            }

            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }
    }

    private String parseAddresses(Address[] address) {
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