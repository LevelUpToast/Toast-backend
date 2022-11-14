package com.levelUpToast.levelUpToast.domain.UseCase.mail;

import java.util.Map;

public interface ExpireCode {
    void expireCode(String userMail, Map<String, String>codeStore);
}
