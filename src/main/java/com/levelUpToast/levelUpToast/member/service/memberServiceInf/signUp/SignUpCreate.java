package com.levelUpToast.levelUpToast.member.service.memberServiceInf.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.member.domain.Member;

public interface SignUpCreate {
    void createMember(Member member) throws LevelUpToastEx;
}
