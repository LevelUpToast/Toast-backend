package com.levelUpToast.levelUpToast.service.login.loginServiceImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.login.loginServiceInf.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SimpleLoginService implements LoginService {

    private final TokenManager tokenManager;
    private final MemberRepository memberRepository;

    @Override
    public Member login(String loginId, String password) throws LevelUpToastEx {
        Optional<Member> findMem = memberRepository.findByLoginId(loginId);
        if (findMem.isEmpty()) {
            log.warn("[LoginService log] : 미존재 ID 로그인 요청  ID = {}, PW = {}", loginId, password);
            throw new LevelUpToastEx("동일한 ID가 존재하지 않습니다.", 2);
        }
        Member member = findMem.get();
        if (member.getPassword().equals(password)) {
            member.setToken(tokenManager.createToken(member.getManageSeq()));
            return member;
        } else {
            log.warn("[LoginService log] : ID와 PW 미일치 ID = {}, PW = {}", loginId, password);
            throw new LevelUpToastEx("ID와 PW가 일치하지 않습니다.", 1);
        }
    }

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
