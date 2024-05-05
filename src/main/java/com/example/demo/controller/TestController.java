package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@RestController
public class TestController {
    private final MailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;

    public TestController(MailSender mailSender, SpringTemplateEngine thymeleafTemplateEngine) {
        this.mailSender = mailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @GetMapping("/sendTestMail")
    public String sendTestMail() {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("h1_text", "Hello World");
        List<String> items = new ArrayList<>();
        items.add("test1");
        items.add("test2");
        thymeleafContext.setVariable("items", items);
        String mailBody = thymeleafTemplateEngine.process("mailTemplate.txt", thymeleafContext);

        simpleMailMessage.setFrom("kaku4n1n@outlook.jp");
        simpleMailMessage.setTo("ryujiokura.bene@gmail.com");
        simpleMailMessage.setSubject("testMailTitle");
        simpleMailMessage.setText(mailBody);

        try {
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }

        return "Test";
    }
}
