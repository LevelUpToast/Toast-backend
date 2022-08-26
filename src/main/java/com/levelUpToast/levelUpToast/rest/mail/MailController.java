package com.levelUpToast.levelUpToast.rest.mail;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.mail.MailResponseForm;
import com.levelUpToast.levelUpToast.domain.mail.Mail;
import com.levelUpToast.levelUpToast.service.e_mail.mailServiceInf.MailService;

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
    public MailResponseForm sendMail(@PathVariable("userMail") String userMail) {
        try {
            mailService.mailSend(new Mail(userMail));
        } catch (LevelUpToastEx e) {
            return new MailResponseForm(e.getERR_CODE(), e.getMessage(), null);
        }
        return new MailResponseForm(-1, "[ " + userMail + " ] 으로 인증 메일을 발송했습니다", null);
    }

    @GetMapping("/mail/verifyCode/{userMail}/{code}")
    public MailResponseForm codeCheck(@PathVariable("userMail") String userMail, @PathVariable("code") String code) throws LevelUpToastEx {

        try {
            String successMail = mailService.codeCheck(userMail, code);
            mailService.expireCode(code);
            return new MailResponseForm(-1, "[ " + successMail + " ] 회원의 인증 코드가 일치합니다.", null);
        } catch (LevelUpToastEx e) {
            return new MailResponseForm(e.getERR_CODE(), e.getMessage(), null);
        }

    }
}
