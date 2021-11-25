package com.demowebshop.utilities;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailUtility {
    public Properties prop;
    FileInputStream file;

    public  void sendEmail(String filePath, String fileName, String rMailId,Properties prop) {
        Properties props = new Properties();
        final String username= prop.getProperty("from_email");
        final String password=prop.getProperty("from_password");
        String eDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Transport transport = session.getTransport();


            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress("test@gmail.com"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rMailId));

            message.setSubject("Demo Web Shop Project_" + eDate);

            BodyPart messageBodyPart = new MimeBodyPart();
            BodyPart attachmentPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            String file = filePath + "//" + fileName;
            DataSource source = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setText("Dear Stakeholder,\n" +
                    "\n" +
                    "These are the test results of \"Demo Web Shop Project\" . Automation execution was conducted on " + eDate + ".\n" +
                    "\n"+
                    "Thanks & Regards,\n" +
                    "Automation Team");
            attachmentPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            transport.connect();
            Transport.send(message);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}