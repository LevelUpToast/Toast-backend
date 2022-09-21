package com.levelUpToast.levelUpToast.service.signUp.signUpServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.signUp.SignUpRequestForm;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface SignUpService {

    Member signUp(SignUpRequestForm form) throws LevelUpToastEx;

}
