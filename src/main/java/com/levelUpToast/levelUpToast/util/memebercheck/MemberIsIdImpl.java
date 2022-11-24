package com.levelUpToast.levelUpToast.util.memebercheck;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.service.memberServiceInf.check.MemberIsID;
import com.levelUpToast.levelUpToast.auth.repository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberIsIdImpl implements MemberIsID {
    private final MemberRepository memberRepository;

    @Override
    public Boolean isMemberID(String id) throws LevelUpToastEx {
        return memberRepository.findByLoginId(id).isPresent();
    }
}
