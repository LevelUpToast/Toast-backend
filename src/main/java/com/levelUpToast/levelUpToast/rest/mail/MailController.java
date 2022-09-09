package com.levelUpToast.levelUpToast.rest.mail;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.model.mail.Mail;
import com.levelUpToast.levelUpToast.service.mail.mailServiceInf.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor

public class MailController {

    private final MailService mailService;

    @GetMapping("/mail/{userMail}")
    public ResponseForm<String> sendMail(@PathVariable("userMail") String userMail) {
        try {
            mailService.mailSend(new Mail(userMail));
        } catch (LevelUpToastEx e) {
            return new ResponseForm<String> (e.getERR_CODE(), e.getMessage(), null);
        }
        return new ResponseForm<String> (-1, "[ " + userMail + " ] 으로 인증 메일을 발송했습니다", null);
    }

    @GetMapping("/mail/{userMail}/{code}")
    public ResponseForm<String>  codeCheck(@PathVariable("userMail") String userMail, @PathVariable("code") String code) throws LevelUpToastEx {
        try {
            String successMail = mailService.codeCheck(userMail, code);
            mailService.expireCode(userMail);
            return new ResponseForm<String> (-1, "[ " + successMail + " ] 메일 주소가 인증 되었습니다.", null);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<String> (e.getERR_CODE(), e.getMessage(), null);
        }
    }
}
