package com.levelUpToast.levelUpToast.member.service.memberServiceInf.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface LogoutMember {
    void logout(String token) throws LevelUpToastEx ;
}
