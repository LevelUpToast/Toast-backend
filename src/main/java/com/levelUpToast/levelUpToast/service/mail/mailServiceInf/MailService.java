package com.levelUpToast.levelUpToast.service.mail.mailServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.mail.Mail;

public interface MailService {

    void mailSend(Mail mail) throws LevelUpToastEx;

    String codeCheck(String userMail,String code) throws LevelUpToastEx;

    void expireCode(String code);

}
