package com.levelUpToast.levelUpToast.config;

import com.levelUpToast.levelUpToast.domain.member.Authority;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        //init Member
        memberRepository.save(new Member("ji", "ji", Authority.ADMIN, "김지용", "970128", "남", "010-6277-0650", "colorful8315@gmail.com", "seoul"));
        memberRepository.save(new Member("mook", "mook", Authority.ADMIN, "임성묵", "000000", "남", "---", "---", "seoul"));
        memberRepository.save(new Member("beom", "beom", Authority.ADMIN, "김준범", "000000", "남", "---", "---", "seoul"));
        memberRepository.save(new Member("saac", "saac", Authority.ADMIN, "이삭", "000000", "남", "---", "---", "seoul"));


    }


}
