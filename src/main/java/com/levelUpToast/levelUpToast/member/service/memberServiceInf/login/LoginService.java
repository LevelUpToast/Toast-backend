package com.levelUpToast.levelUpToast.member.service.memberServiceInf.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.member.domain.Member;

public interface LoginService {
    Member login(String loginId, String password) throws LevelUpToastEx;

    void logout(String token) throws LevelUpToastEx;
}
