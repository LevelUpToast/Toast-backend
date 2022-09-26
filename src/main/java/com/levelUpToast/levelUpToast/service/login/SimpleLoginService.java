package com.levelUpToast.levelUpToast.service.login;

import com.levelUpToast.levelUpToast.domain.UseCase.login.LoginService;
import com.levelUpToast.levelUpToast.domain.UseCase.login.LoginFindMember;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.login.LoginMemberLogout;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SimpleLoginService implements LoginService {

    private final LoginFindMember simpleLoginFindMember;
    private final LoginMemberLogout simpleLoginMemberLogout;

    @Override
    public Member login(String loginId, String password) throws LevelUpToastEx {
        return simpleLoginFindMember.login(loginId, password);
    }

    @Override
    public void logout(String token) throws LevelUpToastEx {
        simpleLoginMemberLogout.logout(token);
    }
}
