package com.levelUpToast.levelUpToast.auth.service.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.service.memberServiceInf.signUp.SignUpCreate;
import com.levelUpToast.levelUpToast.auth.domain.Member;
import com.levelUpToast.levelUpToast.auth.repository.memberRepositoryInf.MemberRepository;
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
