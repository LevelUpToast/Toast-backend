package com.levelUpToast.levelUpToast.domain.UseCase.mail;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

import java.util.Map;

public interface CodeCheckEmail {
    String Check(String userMail, String code, Map<String, String> codeStore) throws LevelUpToastEx;
}
