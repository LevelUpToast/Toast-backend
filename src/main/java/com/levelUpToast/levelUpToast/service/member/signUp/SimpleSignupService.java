package com.levelUpToast.levelUpToast.service.member.signUp;

import com.levelUpToast.levelUpToast.domain.UseCase.member.signUp.SignUpRegister;
import com.levelUpToast.levelUpToast.domain.UseCase.member.signUp.SignUpService;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.signUp.SignUpRequestForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSignupService implements SignUpService {
    private final SignUpRegister signupRegister;
    @Override
    public void signUp(SignUpRequestForm form) throws LevelUpToastEx {
        signupRegister.registerUser(form);
    }
}
