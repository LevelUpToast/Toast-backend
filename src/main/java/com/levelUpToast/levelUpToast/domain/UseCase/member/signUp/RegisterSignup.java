package com.levelUpToast.levelUpToast.domain.UseCase.member.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.signUp.SignUpRequestForm;


public interface RegisterSignup {
     Member registerSignUp(SignUpRequestForm form) throws LevelUpToastEx ;
}
