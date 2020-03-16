/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hi
 */
public class SendMail {
    private String user ="your Email";
    private String pass="password of email";
    
    
    public SendMail(String to,String sub, String otp) {
        Properties prop=new Properties();
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");  
        prop.put("mail.smtp.starttls.enable","true");                                                                     
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        
        Session session;
        session = Session.getInstance(prop,new javax.mail.Authenticator()
        {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()
            {
                return new javax.mail.PasswordAuthentication(user,pass);
            }
        });
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(otp);
            Transport.send(message);
            System.out.println("mail sent");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
