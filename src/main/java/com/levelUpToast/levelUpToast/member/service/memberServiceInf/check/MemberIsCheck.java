package com.levelUpToast.levelUpToast.member.service.memberServiceInf.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.member.domain.Member;

public interface MemberIsCheck {
    Member hasMember(String requestSeq) throws LevelUpToastEx;
}
