package com.levelUpToast.levelUpToast.service.login.loginServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.member.Member;

public interface LoginService {
    Member login(String loginId, String password) throws LevelUpToastEx;

    void logout(String token) throws LevelUpToastEx;
}
