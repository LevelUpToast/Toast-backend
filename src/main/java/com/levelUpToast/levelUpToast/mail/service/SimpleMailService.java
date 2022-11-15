package com.levelUpToast.levelUpToast.mail.service;

import com.levelUpToast.levelUpToast.mail.service.mailServiceInf.ExpireCode;
import com.levelUpToast.levelUpToast.mail.service.mailServiceInf.CodeCheck;
import com.levelUpToast.levelUpToast.mail.service.mailServiceInf.MailService;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.mail.service.mailServiceInf.EmailSend;
import com.levelUpToast.levelUpToast.mail.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMailService implements MailService {

    private final CodeCheck simpleCodeCheck;
    private final ExpireCode simpleExpireCode;
    private final EmailSend simpleEmailSendEmail;

    private final Map<String, String> codeStore = ExpiringMap.builder().maxSize(1000).expirationPolicy(ExpirationPolicy.CREATED).expiration(3, TimeUnit.MINUTES).expirationListener((mail, code) -> log.info("[MailService log] : 메일 인증 코드 시간 만료 제거, mail = {}, code = {} ", mail, code)).build();

    @Override
    public void SendEmail(Mail mail) throws LevelUpToastEx {
        simpleEmailSendEmail.mailSend(mail, codeStore);
    }

    @Override
    public String sentCodeCheck(String userMail, String code) throws LevelUpToastEx {
        return simpleCodeCheck.Check(userMail, code, codeStore);
    }

    @Override
    public void expireCode(String userMail) {
        simpleExpireCode.expireCode(userMail, codeStore);
    }
}
