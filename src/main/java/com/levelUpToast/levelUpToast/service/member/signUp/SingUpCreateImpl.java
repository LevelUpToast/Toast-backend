package com.levelUpToast.levelUpToast.service.member.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.member.signUp.SignUpCreate;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
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
