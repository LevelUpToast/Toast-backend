package com.levelUpToast.levelUpToast.domain.UseCase.member;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface MemberCreate {
    void createMember(Member member) throws LevelUpToastEx;
}
