package com.levelUpToast.levelUpToast.service.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.member.signUp.RegisterSignup;
import com.levelUpToast.levelUpToast.domain.data.member.Authority;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.UseCase.member.MemberCheck;
import com.levelUpToast.levelUpToast.domain.UseCase.member.MemberCreate;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.signUp.SignUpRequestForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberRegisterSignup implements RegisterSignup {
    private final MemberCheck simpleMemberCheck;
    private final MemberCreate simpleMemberCreate;

    @Override
    public Member registerSignUp(SignUpRequestForm form) throws LevelUpToastEx {
        if (simpleMemberCheck.isIDPresent(form.getId())) {
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
        simpleMemberCreate.createMember(member);
        log.info("[SignUpService log] : 회원가입 성공  id = {}, name = {}", form.getId(), form.getName());
        return member;
    }
}
