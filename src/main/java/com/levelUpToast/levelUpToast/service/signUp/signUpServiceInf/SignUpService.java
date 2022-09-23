package com.levelUpToast.levelUpToast.service.signUp.signUpServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.signUp.SignUpRequestForm;
import com.levelUpToast.levelUpToast.domain.model.member.Member;

public interface SignUpService {

    Boolean idPresentCheck(String id) throws LevelUpToastEx;

    Member signUp(SignUpRequestForm form) throws LevelUpToastEx;

}
