package com.levelUpToast.levelUpToast.domain.UseCase.member.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface MemberIsCheck {
    Member hasMember(String requestSeq) throws LevelUpToastEx;
}
