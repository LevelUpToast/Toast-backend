package com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleTokenManager implements TokenManager {
    private Map<String, Long> tokenStore = new ConcurrentHashMap<>();

    @Override
    public String createToken(Long manageSeq) {
        if(tokenStore.values().contains(manageSeq)){
            tokenStore.values().remove(manageSeq);
        }
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, manageSeq);
        return token;
    }

    @Override
    public Long findMemberSeqByToken(String token) throws LevelUpToastEx {
       if(!tokenCheck(token)){
           log.warn("[TokenManager log] : 존재하지 않는 토큰 token = {}",token);
           throw new LevelUpToastEx("존재하지 않는 토큰 입니다.",3);
       }
        return tokenStore.get(token);
    }

    @Override
    public Boolean tokenCheck(String token) {
        return tokenStore.containsKey(token);
    }

    @Override
    public void tokenExpire(String token) throws LevelUpToastEx {
        if(!tokenCheck(token)){
            log.warn("[TokenManager log] : 존재하지 않는 토큰 token = {}",token);
            throw new LevelUpToastEx("존재하지 않는 토큰 입니다.",3);
        }
        tokenStore.remove(token);
    }
}
