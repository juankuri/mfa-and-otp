package org.uv.mfa.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailServiceIm implements MailService {

    private final String from = "juan111pa@gmail.com";
    private final String key = "nabdqczioyrwgmii";

    private final String testTo = "juankupa11@gmail.com";
    private final boolean testing = false;

    @Override
    public void sendEmail(String msg, String to) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, key);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            String finalDestiny = testing ? testTo : to;

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(finalDestiny)
            );
            String texto = "Hello,\n\n"
                    + "You have requested to log in to our system. Your OTP code is:\n\n"
                    + msg + "\n\n"
                    + "This code is valid for 5 minutes and can only be used once.\n\n"
                    + "If you did not request this code, please ignore this email.\n\n"
                    + "Best regards,\nSupport Team.";

            message.setText(texto);

            Transport.send(message);
            System.out.println("Email sent successfully to " + finalDestiny);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }
}
