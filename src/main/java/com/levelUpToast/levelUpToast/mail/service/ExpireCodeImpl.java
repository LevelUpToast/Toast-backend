package com.levelUpToast.levelUpToast.mail.service;

import com.levelUpToast.levelUpToast.mail.service.mailServiceInf.ExpireCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ExpireCodeImpl implements ExpireCode {
    @Override
    public void expireCode(String userMail, Map<String,String> codeStore) {
        codeStore.remove(userMail);
        log.info("[MailService log] : 메일 인증 완료로 인한 코드 제거, code = {} ", userMail);
    }
}
