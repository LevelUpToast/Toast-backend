package com.levelUpToast.levelUpToast.auth.service.login;

import com.levelUpToast.levelUpToast.auth.service.memberServiceInf.login.LoginService;
import com.levelUpToast.levelUpToast.auth.service.memberServiceInf.login.LoginMember;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.service.memberServiceInf.login.LogoutMember;
import com.levelUpToast.levelUpToast.auth.domain.Member;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SimpleLoginService implements LoginService {

    private final LoginMember simpleLoginMember;
    private final LogoutMember simpleLogoutMember;

    @Override
    public Member login(String loginId, String password) throws LevelUpToastEx {
        return simpleLoginMember.login(loginId, password);
    }

    @Override
    public void logout(String token) throws LevelUpToastEx {
        simpleLogoutMember.logout(token);
    }
}
