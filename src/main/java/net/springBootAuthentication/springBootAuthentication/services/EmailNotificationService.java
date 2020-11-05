package net.springBootAuthentication.springBootAuthentication.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.springBootAuthentication.springBootAuthentication.model.MailRequest;
import net.springBootAuthentication.springBootAuthentication.model.MailResponse;


@Service
public class EmailNotificationService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    public MailResponse sendMail(MailRequest mailRequest, Map<String, Object> model){
        MailResponse mResponse = new MailResponse();

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = configuration.getTemplate("emailNotification-template.html");

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setTo(mailRequest.getTo());
            helper.setText(html, true);
			helper.setSubject(mailRequest.getSubject());
			helper.setFrom("LTPCommunity");
            javaMailSender.send(message);
            
            mResponse.setMessage("mail send to : " + mailRequest.getTo());
			mResponse.setStatus(Boolean.TRUE);
        } catch (MessagingException | IOException | TemplateException e) {
            mResponse.setMessage("Mail Sending failure : "+e.getMessage());
			mResponse.setStatus(Boolean.FALSE);
        }

        return mResponse;
    }
  
}
