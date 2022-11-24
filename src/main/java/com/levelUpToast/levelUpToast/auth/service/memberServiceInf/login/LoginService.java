package com.levelUpToast.levelUpToast.auth.service.memberServiceInf.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.domain.Member;

public interface LoginService {
    Member login(String loginId, String password) throws LevelUpToastEx;

    void logout(String token) throws LevelUpToastEx;
}
