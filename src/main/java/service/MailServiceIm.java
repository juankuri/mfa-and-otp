/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

/**
 *
 * @author kuri
 */
public class MailServiceIm implements MailService {

    private final String remitente = "juan111pa@gmail.com";
    private final String clave = "nabdqczioyrwgmii";

    private final String destinoPrueba = "juankupa11@gmail.com";
    private final boolean modoPrueba = false;

    @Override
    public void enviarNotificacion(String mensaje, String destinoUsuario) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));

            String destinoFinal = modoPrueba ? destinoPrueba : destinoUsuario;

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinoFinal)
            );
            String texto = "Hola,\n\n"
                    + "Has solicitado iniciar sesión en nuestro sistema. Tu código OTP es:\n\n"
                    + mensaje + "\n\n"
                    + "Este código es válido por 5 minutos y solo debe ser usado una vez.\n\n"
                    + "Si no solicitaste este código, ignora este correo.\n\n"
                    + "Saludos,\nEquipo de soporte.";

            message.setText(texto);

            Transport.send(message);
            System.out.println("Correo enviado correctamente a " + destinoFinal);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error enviando correo: " + e.getMessage());
        }
    }
}
