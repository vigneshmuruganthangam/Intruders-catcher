package com.example.asus.myapplication;
/**
 * Created by ASUS on 10-06-2018.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail extends AsyncTask<Void,Void,Void> {
    private Context context;
    private Session session;
    private String email;
    private String subject;
    private String message;
    private  String filename;
    private ProgressDialog progressDialog;
    public SendMail(Context context, String emai, String subject, String message,String filename){
        this.context = context;
        this.email = emai;
        this.subject = subject;
        this.message = message;
        this.filename=filename;
    }
    @Override
    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(config.EMAIL, config.PASSWORD);
                    }
                });

        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(email));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mm.setSubject(subject);
            mm.setText(message);
            BodyPart messagebodypart=new MimeBodyPart();
            messagebodypart.setText("intruder");
            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(messagebodypart);
            messagebodypart=new MimeBodyPart();
            DataSource sourc=new FileDataSource(filename);
            messagebodypart.setDataHandler(new DataHandler(sourc));
            messagebodypart.setFileName("image.jpg");
            multipart.addBodyPart(messagebodypart);
            mm.setContent(multipart);
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();

        }
        return null;
    }
}

