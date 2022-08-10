package com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleTokenManager implements TokenManager {
    private final MemberRepository memberRepository;
    private Map<String, Long> tokenStore = new ConcurrentHashMap<>();

    @Override
    public String createToken(Long manageSeq) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, manageSeq);
        return token;
    }

    @Override
    public Member findMemberByToken(String token) throws LevelUpToastEx {
       if(!tokenCheck(token)){
           log.warn("[TokenManager log] : 존재하지 않는 토큰 token = {}",token);
           throw new LevelUpToastEx("존재하지 않는 토큰 입니다.",3);
       }

       Optional<Member> findMem = memberRepository.findByManageSeq(tokenStore.get(token));
       if(findMem.isEmpty()){
           log.warn("[TokenManager log] : 토큰과 일치하는 사용자 미존재 token = {}",token);
           throw new LevelUpToastEx("토큰과 일치하는 사용자가 존재하지 않습니다.",4);
       }
       return findMem.get();
    }

    @Override
    public Boolean tokenCheck(String token) {
        return tokenStore.containsKey(token);
    }

    @Override
    public void tokenExpire(String token) throws Exception {
        if(!tokenCheck(token)){
            log.warn("[TokenManager log] : 존재하지 않는 토큰 token = {}",token);
            throw new LevelUpToastEx("존재하지 않는 토큰 입니다.",3);
        }
        tokenStore.remove(token);
    }
}
