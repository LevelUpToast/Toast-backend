package com.levelUpToast.levelUpToast.domain.UseCase.member.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface LogoutMember {
    void logout(String token) throws LevelUpToastEx ;
}
