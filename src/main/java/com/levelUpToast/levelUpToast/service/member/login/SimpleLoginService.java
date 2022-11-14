package com.levelUpToast.levelUpToast.service.member.login;

import com.levelUpToast.levelUpToast.domain.UseCase.member.login.LoginService;
import com.levelUpToast.levelUpToast.domain.UseCase.member.login.LoginMember;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.member.login.LogoutMember;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
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
