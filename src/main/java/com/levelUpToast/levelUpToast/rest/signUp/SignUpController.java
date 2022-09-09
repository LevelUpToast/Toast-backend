package com.levelUpToast.levelUpToast.rest.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.signUp.SignUpRequestForm;
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

    @PostMapping("/signup")
    public ResponseForm<String> signUp(@RequestBody SignUpRequestForm form) {
        try {
            signUpService.signUp(form);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
        return new ResponseForm<>(-1, "회원가입 성공", null);
    }

}
