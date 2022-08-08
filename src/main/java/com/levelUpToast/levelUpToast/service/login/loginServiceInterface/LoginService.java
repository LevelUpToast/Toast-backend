package com.levelUpToast.levelUpToast.service.login.loginServiceInterface;

import com.levelUpToast.levelUpToast.domain.member.Member;

public interface LoginService {
    Member login(String loginId, String password) throws Exception;
}
