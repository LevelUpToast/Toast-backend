package com.levelUpToast.levelUpToast.mail.service.mailServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

import java.util.Map;

public interface CodeCheck {
    String Check(String userMail, String code, Map<String, String> codeStore) throws LevelUpToastEx;
}
