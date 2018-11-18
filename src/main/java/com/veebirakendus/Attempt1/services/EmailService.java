package com.veebirakendus.Attempt1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(String toEmail,String name, Long id, String description) throws Exception {

        System.out.println("siin1");

        MimeMessage message = sender.createMimeMessage();

        System.out.println("siin2");

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(toEmail);

        helper.setText("Kasutaja "+name+" on huvitatud "+"young-taiga-97700.herokuapp.com/kuulutusInfo/"+id+" laenutamisest");

        helper.setSubject(description);

        sender.send(message);

    }


}
