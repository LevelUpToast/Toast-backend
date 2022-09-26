package com.levelUpToast.levelUpToast.domain.UseCase.mail;

import java.util.Map;

public interface ExpireCodeEmail {
    void expireCode(String userMail, Map<String, String>codeStore);
}
