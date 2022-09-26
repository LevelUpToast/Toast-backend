package com.levelUpToast.levelUpToast.controller;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.data.mail.Mail;
import com.levelUpToast.levelUpToast.domain.UseCase.mail.MailService;

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
            mailService.SendEmail(new Mail(userMail));
        } catch (LevelUpToastEx e) {
            return new ResponseForm<> (e.getERR_CODE(), e.getMessage(), null);
        }
        return new ResponseForm<> (-1, "[ " + userMail + " ] 으로 인증 메일을 발송했습니다", null);
    }

    @GetMapping("/mail/{userMail}/{code}")
    public ResponseForm<String>  codeCheck(@PathVariable("userMail") String userMail, @PathVariable("code") String code) throws LevelUpToastEx {
        try {
            String successMail = mailService.sentCodeCheck(userMail, code);
            mailService.expireCode(userMail);
            return new ResponseForm<> (-1, "[ " + successMail + " ] 메일 주소가 인증 되었습니다.", null);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<> (e.getERR_CODE(), e.getMessage(), null);
        }
    }
}
