import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class SmtpSender {

    public static void sendEmail() {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", Main.host_smtp);
        properties.put("mail.smtp.SocketFactory.port", Main.port_smtp);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.auth", "true");
        properties.put("mail.smtp.port", Main.port_smtp);

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Main.username, Main.password);
            }
        };

        Session session = Session.getDefaultInstance(properties, auth);

        try {
            Scanner scanner = new Scanner(System.in);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Main.username));

            System.out.println("Give receivers for email:");
            String receiverEmail = scanner.nextLine();

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
            System.out.println("Give subject for email: ");
            String subjectEmail = scanner.nextLine();
            message.setSubject(subjectEmail);
            System.out.println("Give text for email: ");
            String textEmail = scanner.nextLine();

            System.out.print("\nDo you want to send attachment [y/n] : ");
            String ans = scanner.nextLine();
            if ("Y".equals(ans) || "y".equals(ans)) {
                message = (MimeMessage) setAttachment(message, textEmail);
            }

            System.out.println("Sending...");
            Transport.send(message, Main.username, Main.password);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object setAttachment(MimeMessage mimeMessage, String textEmail) throws MessagingException {

        Scanner scanner = new Scanner(System.in);
        Multipart multipart = new MimeMultipart();
        MimeBodyPart attachmentPart = new MimeBodyPart();
        MimeBodyPart textPart = new MimeBodyPart();

        try {
            System.out.println("Type full path to the attachment on PC:");
            String filename = scanner.nextLine();
            File f = new File(filename);

            attachmentPart.attachFile(f);
            textPart.setText(textEmail);
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mimeMessage.setContent(multipart);

        return mimeMessage;
    }

    public static void replyToMessage(Message message) throws MessagingException {

        String to = InternetAddress.toString(message.getRecipients(Message.RecipientType.TO));

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you want to reply [y/n] : ");
        String ans = scanner.nextLine();
        if ("Y".equals(ans) || "y".equals(ans)) {

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", Main.host_smtp);
            properties.put("mail.smtp.SocketFactory.port", Main.port_smtp);
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.ssl.auth", "true");
            properties.put("mail.smtp.port", Main.port_smtp);

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Main.username, Main.password);
                }
            };

            Session session = Session.getInstance(properties, auth);

            MimeMessage replyMessage = new MimeMessage(session);
            replyMessage = (MimeMessage) message.reply(false);
            replyMessage.setFrom(new InternetAddress(to));

            System.out.println("Give text for email: ");
            String textEmail = scanner.nextLine();
            replyMessage.setText(textEmail);

            replyMessage.setReplyTo(message.getReplyTo());

            Transport t = session.getTransport("smtp");
            try {
                t.connect(Main.username, Main.password);
                t.sendMessage(replyMessage, replyMessage.getAllRecipients());
            } finally {
                t.close();
            }
            System.out.println("Message replied successfully.");

        } else if ("n".equals(ans)) {
            return;
        }
    }
}