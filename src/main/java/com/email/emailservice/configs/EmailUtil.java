package com.email.emailservice.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service("emailUtil")
public class EmailUtil {

    @Value("${sender.email}")
    private String senderEmail;

    @Value("${sender.app.password}")
    private String appPassword;

    /**
     * Utility method to send simple HTML email
     *
     * @param toEmail
     * @param subject
     * @param body
     */
    public void sendEmail( String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(getSession());
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Session getSession() {
        //SMTP -> Simple Mail Transfer Protocol.
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            // Override the getPasswordAuthentication method
            // https://myaccount.google.com/apppasswords
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("senderEmail = " +senderEmail);
                System.out.println("appPassword = " + appPassword);
                return new PasswordAuthentication(senderEmail,appPassword);
            }
        };
        return Session.getInstance(props, auth);
    }

    @PostConstruct
    public void init() {
        System.out.println("Sender Email: " + senderEmail);
        System.out.println("App Password: " + appPassword);
    }
}