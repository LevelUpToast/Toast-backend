package com.levelUpToast.levelUpToast.mail.service.mailServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.mail.domain.Mail;

import java.util.Map;

public interface EmailSend {
     void mailSend(Mail mail,  Map<String,String> codeStore) throws LevelUpToastEx ;
}
