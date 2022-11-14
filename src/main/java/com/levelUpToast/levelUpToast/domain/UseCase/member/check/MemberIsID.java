package com.levelUpToast.levelUpToast.domain.UseCase.member.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface MemberIsID {
    Boolean isMemberID(String id) throws LevelUpToastEx;
}
