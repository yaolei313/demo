package com.yao.app.demo.biz;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    private static final String REGISTER_EMAIL_TEMPLATE = "register_email.html";

    private static final String REGISTER_EMAIL_SUBJECT = "register success";

    @Value("${spring.mail.username}")
    private String sender;

    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    @Autowired
    public MailService(JavaMailSender mailSender,
        TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendWelcomeRegisterEmailQuietly(String email, String nickname) {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("name", nickname);

        Context context = new Context();
        context.setVariables(variables);
        String content = templateEngine.process(REGISTER_EMAIL_TEMPLATE, context);

        sendEmailQuietly(email, REGISTER_EMAIL_SUBJECT, content);
    }

    // ----

    private void sendEmailQuietly(String to, String subject, String htmlContent) {
        try {
            mailSender.send(mimeMessage -> {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom(sender);
                message.setTo(to);
                message.setSubject(subject);
                message.setText(htmlContent, true);
            });
            LOG.warn("send email to:{} success", to);
        } catch (Exception e) {
            LOG.warn("send email to:{} fail", to, e);
        }
    }

}
