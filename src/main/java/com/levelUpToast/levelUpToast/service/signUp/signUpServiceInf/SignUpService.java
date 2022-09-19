package com.levelUpToast.levelUpToast.service.signUp.signUpServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.signUp.SignUpRequestForm;
import com.levelUpToast.levelUpToast.domain.model.member.Member;

public interface SignUpService {

    Member signUp(SignUpRequestForm form) throws LevelUpToastEx;

}
