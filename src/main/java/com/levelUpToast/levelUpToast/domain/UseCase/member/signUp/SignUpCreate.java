package com.levelUpToast.levelUpToast.domain.UseCase.member.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface SignUpCreate {
    void createMember(Member member) throws LevelUpToastEx;
}
