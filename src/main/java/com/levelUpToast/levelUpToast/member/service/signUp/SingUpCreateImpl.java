package com.levelUpToast.levelUpToast.member.service.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.signUp.SignUpCreate;
import com.levelUpToast.levelUpToast.member.domain.Member;
import com.levelUpToast.levelUpToast.member.repository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SingUpCreateImpl implements SignUpCreate {
    final MemberRepository memberRepository;
    @Override
    public void createMember(Member member) throws LevelUpToastEx {
        memberRepository.save(member);
    }
}
