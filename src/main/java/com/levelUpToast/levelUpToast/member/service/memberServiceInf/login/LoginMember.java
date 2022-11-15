package com.levelUpToast.levelUpToast.member.service.memberServiceInf.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.member.domain.Member;

public interface LoginMember {
    Member login(String loginId, String password) throws LevelUpToastEx;
}
