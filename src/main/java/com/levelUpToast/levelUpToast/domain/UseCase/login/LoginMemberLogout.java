package com.levelUpToast.levelUpToast.domain.UseCase.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface LoginMemberLogout {
    void logout(String token) throws LevelUpToastEx ;
}
