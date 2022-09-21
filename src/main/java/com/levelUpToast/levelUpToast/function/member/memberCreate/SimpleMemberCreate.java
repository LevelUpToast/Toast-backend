package com.levelUpToast.levelUpToast.function.member.memberCreate;

import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimpleMemberCreate implements MemberCreate{
    final MemberRepository memberRepository;

    public void createMember(Member member){
        memberRepository.save(member);
    }
}
