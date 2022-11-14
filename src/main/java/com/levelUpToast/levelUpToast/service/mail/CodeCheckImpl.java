package com.levelUpToast.levelUpToast.service.mail;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.mail.CodeCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CodeCheckImpl implements CodeCheck {
    @Override
    public String Check(String userMail, String code, Map<String, String> codeStore) throws LevelUpToastEx {
        if (codeStore.containsKey(userMail)) {
            if (codeStore.get(userMail).equals(code)) {
                return userMail;
            } else {
                log.warn("[MailService log] : 메일 인증 코드 미일치, mail = {}, code = {} ", userMail, code);
                throw new LevelUpToastEx("메일 인증 코드가 일치하지 않습니다.", 24);
            }
        }
        log.warn("[MailService log] : 만료 혹은 존재하지 않는 메일 인증 코드, mail = {}, code = {} ", userMail, code);
        throw new LevelUpToastEx("존재하지 않는 메일 인증 코드 입니다.", 25);

    }
}
