package com.levelUpToast.levelUpToast.service.login.loginServiceImpl;

import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.login.loginServiceInterface.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class SimpleLoginService implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String loginId, String password) {

        return null;
    }
}
