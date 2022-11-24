package com.levelUpToast.levelUpToast.auth.service.memberServiceInf.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.domain.Member;

public interface UserIntegrityVerification {
    Boolean isIDPresent(String id) throws LevelUpToastEx;

    Member isMember(String requestSeq) throws LevelUpToastEx;
}
