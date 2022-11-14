package com.levelUpToast.levelUpToast.service.mail;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.mail.EmailSend;
import com.levelUpToast.levelUpToast.domain.data.mail.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
public class EmailSendImpl implements EmailSend {
    @Value("${mail.smtp.id}")
    private String id;

    @Value("${mail.smtp.pw}")
    private String pw;

    @Value("${mail.smtp.domain}")
    private String smtpDomain;
    @Value("${mail.smtp.port}")
    private int smtpPort;

    final int codeMax = 123123;
    final int codeMin = 982545;

    @Override
    public void mailSend(Mail mail, Map<String,String> codeStore) throws LevelUpToastEx {

        // mail 발송 전 codeStore 에서 중복 mail 제거
        codeStore.remove(mail.getToAddress());

        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpDomain);
        prop.put("mail.smtp.port", smtpPort);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", smtpDomain);

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(id, pw);
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(id));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(mail.getToAddress())));
            mail.setCode(String.valueOf((int) (Math.random() * (codeMax - codeMin) + codeMin)));
            message.setSubject("LevelUpToast 인증 코드 발송");
            message.setText("인증코드 : " + mail.getCode());
            Transport.send(message);

            log.info("[MailService log] : 메일 인증 코드 발송 완료, mail = {} ", mail.getToAddress());
            codeStore.put(mail.getToAddress(), mail.getCode());
        } catch (Exception e) {
            log.warn("[MailService log] : mail Exception , mail = {}, Exception info ", mail.getToAddress(), e);
            throw new LevelUpToastEx("mail service 오류", 22);
        }

    }
}
