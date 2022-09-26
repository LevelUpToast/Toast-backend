package com.levelUpToast.levelUpToast.domain.UseCase.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface LoginFindMember {
    Member login(String loginId, String password) throws LevelUpToastEx;
}
