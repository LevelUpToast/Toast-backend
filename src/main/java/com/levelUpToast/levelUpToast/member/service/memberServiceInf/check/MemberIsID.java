package com.levelUpToast.levelUpToast.member.service.memberServiceInf.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface MemberIsID {
    Boolean isMemberID(String id) throws LevelUpToastEx;
}
