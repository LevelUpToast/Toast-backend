package com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf;

import com.levelUpToast.levelUpToast.domain.member.Member;

public interface TokenManager {

    //token 생성 및 저장
    String createToken(Long manageSeq);

    //token 조회 (로그인 완료 사용자 데이터 조회)
    Member findMemberByToken(String token) throws Exception;

    //token 만료 조회 (로그인 완료 확인용)
    Boolean tokenCheck(String token);

    //token 만료
    void tokenExpire(String token) throws Exception;
}
