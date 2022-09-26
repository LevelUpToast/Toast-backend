package com.levelUpToast.levelUpToast.domain.UseCase.member;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface MemberCheck {
    Boolean idPresentCheck(String id);

    Member checkMember(String requestSeq) throws LevelUpToastEx;
}
