package com.levelUpToast.levelUpToast.service.login.loginServiceImpl;

import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.login.loginServiceInterface.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @AllArgsConstructor
public class SimpleLoginService implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String loginId, String password) throws Exception {
        Optional<Member> findMem = memberRepository.findByloginId(loginId);
        if (findMem.isEmpty()) {
            return null;
        }
        Member member = findMem.get();

        if (member.getPassword().equals(password)) {
            return member;
        } else {
            throw new Exception("ID와 PW가 일치하지 않습니다.");
        }
    }
}
