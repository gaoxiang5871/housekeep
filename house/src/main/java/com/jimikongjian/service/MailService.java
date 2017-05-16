package com.jimikongjian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by baidu on 17/2/12.
 * 建立Email的类,实现对Email的检测,和自动发送邮件等功能
 */
@Service("mailService")
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.form}")
    private String form;

    @Value("${spring.mail.subject}")
    private String subject;

    @Value("${spring.mail.text}")
    private String mailText;


    public void sendMail(String email, String code){
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

        try {
            message.setFrom(form);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(mailText+code);
            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 检测邮箱地址的合法性
     * @param email
     * @return 如果邮箱地址合法则返回true,否则返回false
     */
    public boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+@(\\w+.)+com"); //简单匹配
//        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
