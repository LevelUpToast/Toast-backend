package com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerImpl;

import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
    public Member findMemberByToken(String token) throws Exception {
       if(!tokenCheck(token)){
           throw new Exception("존재하지 않는 토큰 입니다.");
       }

       Optional<Member> findMem = memberRepository.findByManageSeq(tokenStore.get(token));
       if(findMem.isEmpty()){
           throw new Exception("토큰과 일치하는 사용자가 존재하지 않습니다.");
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
            throw new Exception("존재하지 않는 토큰 입니다.");
        }
        tokenStore.remove(token);
    }
}
