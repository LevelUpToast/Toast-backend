package com.levelUpToast.levelUpToast.mail.service.mailServiceInf;

import java.util.Map;

public interface ExpireCode {
    void expireCode(String userMail, Map<String, String>codeStore);
}
