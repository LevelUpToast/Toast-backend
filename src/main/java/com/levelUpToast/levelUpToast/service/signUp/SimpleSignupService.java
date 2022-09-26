package com.levelUpToast.levelUpToast.service.signUp;

import com.levelUpToast.levelUpToast.domain.UseCase.member.signUp.RegisterSignup;
import com.levelUpToast.levelUpToast.domain.UseCase.member.signUp.SignupService;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.signUp.SignUpRequestForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSignupService implements SignupService {
    private final RegisterSignup simpleRegisterSignup;
    @Override
    public void signUp(SignUpRequestForm form) throws LevelUpToastEx {
        simpleRegisterSignup.registerSignUp(form);
    }
}
