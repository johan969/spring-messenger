package se.iths.johan.springmessenger.messaging;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.stereotype.Component;
import se.iths.johan.springmessenger.model.Email;
import se.iths.johan.springmessenger.model.Message;

import java.util.Properties;

@Component("email")
public class EmailSender implements Messenger {

    private static final String FROM = "safe.webshop.iths@gmail.com";
    private static final String APP_PASSWORD = "igfw qlbe qita ydhm";

    @Override
    public void send(Message message) {
        //Implementation av den abstrakta metoden send i interface messanger
        //Här har vi tillgång till email-variabel


        if (!(message instanceof Email email)){
            throw new IllegalArgumentException("Message type not supported");
        }


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, APP_PASSWORD);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(FROM));
            mimeMessage.setRecipient(jakarta.mail.Message.RecipientType.TO,
                    new InternetAddress(email.getRecipient()));
            
            mimeMessage.setSubject(email.getSubject());
            mimeMessage.setText(email.getMessage());
            Transport.send(mimeMessage);
            
            System.out.println("Sent message successfully....");
        }catch (Exception e){
            System.out.println("Email sending failed....");
        }

    }

}
