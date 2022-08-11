package com.levelUpToast.levelUpToast.service.login.loginServiceInterface;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.member.Member;

public interface LoginService {
    Member login(String loginId, String password) throws LevelUpToastEx;
}
