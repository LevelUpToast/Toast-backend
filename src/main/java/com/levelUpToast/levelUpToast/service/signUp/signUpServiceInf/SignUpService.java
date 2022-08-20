package com.levelUpToast.levelUpToast.service.signUp.signUpServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.signUp.SignUpRequestForm;
import com.levelUpToast.levelUpToast.domain.member.Member;

public interface SignUpService {

    Boolean idPresentCheck(String id);

    Member signUp(SignUpRequestForm form) throws LevelUpToastEx;

}