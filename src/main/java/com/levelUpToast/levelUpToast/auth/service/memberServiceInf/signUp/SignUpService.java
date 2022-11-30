package com.levelUpToast.levelUpToast.auth.service.memberServiceInf.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.signUp.SignUpRequestForm;

public interface SignUpService {

    void signUp(SignUpRequestForm form) throws LevelUpToastEx;

}
