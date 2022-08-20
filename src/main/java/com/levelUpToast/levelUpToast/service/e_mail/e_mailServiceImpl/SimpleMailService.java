package com.levelUpToast.levelUpToast.service.e_mail.e_mailServiceImpl;

import com.levelUpToast.levelUpToast.domain.mail.Mail;
import com.levelUpToast.levelUpToast.service.e_mail.e_mailServiceInf.MailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SimpleMailService implements MailService {

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "colorful8315@naver.com";

    @Override
    public String mailSend(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom(SimpleMailService.FROM_ADDRESS);
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        mailSender.send(message);

        return mail.getMessage();
    }
}
