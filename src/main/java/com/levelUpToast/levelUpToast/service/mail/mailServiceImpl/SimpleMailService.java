package com.levelUpToast.levelUpToast.service.mail.mailServiceImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.mail.Mail;
import com.levelUpToast.levelUpToast.service.mail.mailServiceInf.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor

public class SimpleMailService implements MailService {

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


    private final Map<String, String> codeStore = ExpiringMap.builder()
            .maxSize(1000)
            .expirationPolicy(ExpirationPolicy.CREATED)
            .expiration(3, TimeUnit.MINUTES)
            .expirationListener((mail, code) -> log.info("[MailService log] : 메일 인증 코드 시간 만료 제거, mail = {}, code = {} ", mail, code))
            .build();

    @Override
    public void mailSend(Mail mail) throws LevelUpToastEx {

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
            mail.setCode(String.valueOf((int)(Math.random() * (codeMax - codeMin) + codeMin)));
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

    @Override
    public String codeCheck(String userMail, String code) throws LevelUpToastEx {
        if (codeStore.containsKey(userMail)) {
            if (codeStore.get(userMail).equals(code)) {
                return userMail;
            } else {
                log.warn("[MailService log] : 메일 인증 코드 미일치, mail = {}, code = {} ", userMail, code);
                throw new LevelUpToastEx("메일 인증 코드가 일치하지 않습니다.", 24);
            }
        }
        log.warn("[MailService log] : 만료 혹은 존재하지 않는 메일 인증 코드, mail = {}, code = {} ", userMail, code);
        throw new LevelUpToastEx("존재하지 않는 메일 인증 코드 입니다.", 25);

    }

    @Override
    public void expireCode(String userMail) {
        codeStore.remove(userMail);
        log.info("[MailService log] : 메일 인증 완료로 인한 코드 제거, code = {} ", userMail);
    }
}
