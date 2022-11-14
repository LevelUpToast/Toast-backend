package com.levelUpToast.levelUpToast.domain.UseCase.member.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface UserIntegrityVerification {
    Boolean isIDPresent(String id) throws LevelUpToastEx;

    Member isMember(String requestSeq) throws LevelUpToastEx;
}
