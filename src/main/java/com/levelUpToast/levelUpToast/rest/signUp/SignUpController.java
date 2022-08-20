package com.levelUpToast.levelUpToast.rest.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.signUp.SignUpRequestForm;
import com.levelUpToast.levelUpToast.domain.dataForm.signUp.SignUpResponseForm;
import com.levelUpToast.levelUpToast.service.signUp.signUpServiceInf.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/signUp")
    public SignUpResponseForm signUp(@RequestBody SignUpRequestForm form) {

        try {
            signUpService.signUp(form);
        } catch (LevelUpToastEx e) {
            return new SignUpResponseForm(e.getERR_CODE(), e.getMessage(), null);
        }
        return new SignUpResponseForm(-1, "회원가입 성공", null);
    }

}
