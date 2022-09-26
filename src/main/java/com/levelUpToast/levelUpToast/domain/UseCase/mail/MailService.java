package com.levelUpToast.levelUpToast.domain.UseCase.mail;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.mail.Mail;

public interface MailService {

    void SendEmail(Mail mail) throws LevelUpToastEx;

    String sentCodeCheck(String userMail, String code) throws LevelUpToastEx;

    void expireCode(String code);

}
