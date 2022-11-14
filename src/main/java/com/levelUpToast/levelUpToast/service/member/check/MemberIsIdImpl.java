package com.levelUpToast.levelUpToast.service.member.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.member.check.MemberIsID;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberIsIdImpl implements MemberIsID {
    private final MemberRepository memberRepository;

    @Override
    public Boolean isMemberID(String id) throws LevelUpToastEx {
        return memberRepository.findByloginId(id).isPresent();
    }
}
