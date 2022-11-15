package com.levelUpToast.levelUpToast.mail.service.mailServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.mail.domain.Mail;

public interface MailService {

    void SendEmail(Mail mail) throws LevelUpToastEx;

    String sentCodeCheck(String userMail, String code) throws LevelUpToastEx;

    void expireCode(String code);

}
