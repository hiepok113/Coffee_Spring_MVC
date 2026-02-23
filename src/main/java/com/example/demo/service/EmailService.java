package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Gửi dạng chữ Text thô sơ (Như lúc nãy)
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("thanhhiep.hongduc@gmail.com"); 
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        
        javaMailSender.send(message);
    }
    
    // GỬI DẠNG ĐỒ HỌA HTML CAO CẤP (XỊN)
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            // "true" = Cho phép đính kèm tệp tin và Render cấu trúc HTML
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("thanhhiep.hongduc@gmail.com"); 
            helper.setTo(to);
            helper.setSubject(subject);
            
            // Tham số true thần thánh: Báo cho Google biết đây là mã HTML chứ không phải chữ thường
            helper.setText(htmlBody, true); 
            
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
