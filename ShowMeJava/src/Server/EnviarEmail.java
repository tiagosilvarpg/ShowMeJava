/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.Properties;/*
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/
/**
 *
 * @author Tiago
 */
public class EnviarEmail {
    public static final String EMAIL="ShowMeNoReply@gmail.com",SENHA="=}7F6tbue'>(C#VdT5US3HPvc-aeH";
    static Properties props;
    public static void NovoEmail(String to,String assunto,String corpo){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        /*
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(EMAIL, SENHA);
              }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL)); //Remetente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(assunto);//Assunto
            message.setText(corpo);
            Transport.send(message);
            
            System.out.println("email enviado para "+to);
            } 
        catch (MessagingException e) {
            System.out.println(e.getMessage());
        }*/
    }
    
}
