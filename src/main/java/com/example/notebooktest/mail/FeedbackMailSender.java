package com.example.notebooktest.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


import java.util.Properties;

@Component
public class FeedbackMailSender implements FeedbackSender {

    private JavaMailSenderImpl mailSender;
    private Properties mailProperties = new Properties();

    public FeedbackMailSender(Environment environment) {
        mailSender = new JavaMailSenderImpl();
        mailProperties.put("mail.smtp.starttls.enable", true);

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));

    }

    @Override
    public void sendFeedback(String from, String name, String feedback) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("ivan5656@list.ru");
        message.setSubject("New feedback from" + name);
        message.setText(feedback);
        message.setFrom(from);

        mailSender.send(message);
    }

//    public FeedbackMailSender(JavaMailSenderImpl mailSender) {
//        this.mailSender = mailSender;
//    }

    //    public void sendFeedback(String from,
//                             String name,
//                             String feedback){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setSubject("New feedback from " + name);
//        message.setText(feedback);
//        message.setTo("ivan5656@list.ru");
//
//        mailSender.send(message);
//    }
}
