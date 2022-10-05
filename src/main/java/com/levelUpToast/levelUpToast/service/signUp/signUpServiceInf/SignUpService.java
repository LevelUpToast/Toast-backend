package com.levelUpToast.levelUpToast.domain.UseCase.member.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.signUp.SignUpRequestForm;

public interface SignupService {

    void signUp(SignUpRequestForm form) throws LevelUpToastEx;

}
