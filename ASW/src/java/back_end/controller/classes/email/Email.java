/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.controller.classes.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 *
 * @author epilif3sotnas
 */
public class Email {
    private final String from = "EmailToSend@utad.pt";
    private final String host = "localhost";
    
    public boolean sendEmail (String to) throws MessagingException {
        return true;
//            Properties properties = System.getProperties();
//            properties.setProperty("mail.smtp.host", host);
//            Session session = Session.getDefaultInstance(properties);
//            
//            try {
//                MimeMessage message = new MimeMessage(session);
//                message.setFrom(new InternetAddress(from));
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//                message.setSubject("Recover account");
//                message.setText("Email of recuperation", "UTF-8");
//                
//                Transport.send(message);
//                
//                return true;
//            } catch (MessagingException ex) {
//                return false;
//            }
    }
}
