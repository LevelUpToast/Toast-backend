package com.levelUpToast.levelUpToast.auth.service.memberServiceInf.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.domain.Member;

public interface MemberIsCheck {
    Member hasMember(String requestSeq) throws LevelUpToastEx;
}
