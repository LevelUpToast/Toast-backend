package com.levelUpToast.levelUpToast.member.service.memberServiceInf.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.signUp.SignUpRequestForm;


public interface SignUpRegister {
     void registerUser(SignUpRequestForm form) throws LevelUpToastEx ;
}
