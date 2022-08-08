package com.levelUpToast.levelUpToast.config;

import com.levelUpToast.levelUpToast.domain.member.Authority;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        //init Member
        memberRepository.save(new Member("kim", "kim", Authority.ADMIN, "지용", "970128", "남", "010-6277-0650", "colorful8315@gmail.com", "seoul"));

    }


}
