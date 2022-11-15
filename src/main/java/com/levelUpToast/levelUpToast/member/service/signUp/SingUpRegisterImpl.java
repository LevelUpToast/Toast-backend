package com.levelUpToast.levelUpToast.member.service.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.signUp.SignUpRegister;
import com.levelUpToast.levelUpToast.member.domain.Authority;
import com.levelUpToast.levelUpToast.member.domain.Member;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.check.UserIntegrityVerification;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.signUp.SignUpCreate;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.signUp.SignUpRequestForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SingUpRegisterImpl implements SignUpRegister {
    private final UserIntegrityVerification simpleUserIntegrityVerification;
    private final SignUpCreate simpleSignUpCreate;

    @Override
    public void registerUser(SignUpRequestForm form) throws LevelUpToastEx {
        if (simpleUserIntegrityVerification.isIDPresent(form.getId())) {
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
        simpleSignUpCreate.createMember(member);
        log.info("[SignUpService log] : 회원가입 성공  id = {}, name = {}", form.getId(), form.getName());
    }
}
