package com.levelUpToast.levelUpToast.member.service.login;


import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.login.LogoutMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutMemberImpl implements LogoutMember {
    private final TokenManager tokenManager;
    @Override
    public void logout(String token) throws LevelUpToastEx {
        try {
            tokenManager.tokenExpire(token);
            log.info("[LoginService log] : 로그아웃 성공 token = {}", token);
        } catch (LevelUpToastEx e) {
            throw e;
        }
    }
}
