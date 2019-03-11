package com.apigateway.mail;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class FeedbackMailSender implements FeedbackSender {
    private JavaMailSenderImpl mailSender;

    public FeedbackMailSender(Environment environment){
        mailSender = new JavaMailSenderImpl();

        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));

        Properties properties = new Properties();

        properties.setProperty("mail.smtp.starttls.enable","true");properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.required", "true");

        mailSender.setJavaMailProperties(properties);

    }

    @Override
    public void sendFeedback(String from, String name, String feedback){
        System.out.println("Setting all the parameters for mail");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("nayarrahul8@gmail.com");
        message.setSubject("New feedback from " + name);
        message.setText(feedback);
        message.setFrom(from);
        this.mailSender.send(message);


    }
}
