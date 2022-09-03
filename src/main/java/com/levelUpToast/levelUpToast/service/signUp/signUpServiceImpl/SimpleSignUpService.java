package com.levelUpToast.levelUpToast.service.signUp.signUpServiceImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.signUp.SignUpRequestForm;
import com.levelUpToast.levelUpToast.domain.member.Authority;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.signUp.signUpServiceInf.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSignUpService implements SignUpService {

    private final MemberRepository memberRepository;

    @Override
    public Boolean idPresentCheck(String id) {
        return memberRepository.findByloginId(id).isPresent();
    }

    @Override
    public Member signUp(SignUpRequestForm form) throws LevelUpToastEx {
        if(idPresentCheck(form.getId())){
            log.warn("[SignUpService log] : 이미 존재하는 id 회원가입 요청 id = {}", form.getId());
            throw new LevelUpToastEx("이미 존재하는 id 입니다", 11);
        }
        Member member =
                new Member(
                        form.getId(),
                        form.getPw(),
                        Authority.MEM,
                        form.getName(),
                        form.getGender(),
                        form.getPhoneNumber(),
                        form.getE_mail(),
                        form.getAddress());
        memberRepository.save(member);
        log.info("[SignUpService log] : 회원가입 성공  id = {}, name = {}", form.getId(),form.getName());
        return member;
    }
}
