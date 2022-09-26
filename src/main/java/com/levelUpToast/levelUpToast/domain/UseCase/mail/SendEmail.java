package com.levelUpToast.levelUpToast.domain.UseCase.mail;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.mail.Mail;

import java.util.Map;

public interface SendEmail {
     void mailSend(Mail mail,  Map<String,String> codeStore) throws LevelUpToastEx ;
}
