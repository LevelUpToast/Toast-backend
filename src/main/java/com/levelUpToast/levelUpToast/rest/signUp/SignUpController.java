package com.levelUpToast.levelUpToast.rest.signUp;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.signUp.SignUpRequestForm;
import com.levelUpToast.levelUpToast.service.signUp.signUpServiceInf.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    /**
     * signup 회원가입을 받고 요청하는 컨트롤러
     * @param form 클라이언트로 부터 받은 Body
     * @return 요청이 끝나면 ResponseForm 반환한다.
     */
    @PostMapping("/signup")
    public ResponseForm<String> signUp(@RequestBody SignUpRequestForm form) {
        try {
            signUpService.signUp(form);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
        return new ResponseForm<>(-1, "회원가입 성공", null);
    }

}
