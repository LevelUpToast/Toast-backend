package com.levelUpToast.levelUpToast.auth.service.memberServiceInf.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.domain.Member;

public interface SignUpCreate {
    void createMember(Member member) throws LevelUpToastEx;
}
