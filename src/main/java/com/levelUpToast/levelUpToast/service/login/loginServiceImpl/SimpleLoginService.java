package com.levelUpToast.levelUpToast.service.login.loginServiceImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
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
    public Member login(String loginId, String password) throws LevelUpToastEx {
        Optional<Member> findMem = memberRepository.findByloginId(loginId);
        if (findMem.isEmpty()) {
            throw new LevelUpToastEx("동일한 ID가 존재하지 않습니다.",2);
        }
        Member member = findMem.get();

        if (member.getPassword().equals(password)) {
            return member;
        } else {
            throw new LevelUpToastEx("ID와 PW가 일치하지 않습니다.",1);
        }
    }
}
