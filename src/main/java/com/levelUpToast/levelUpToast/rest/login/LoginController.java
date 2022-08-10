package com.levelUpToast.levelUpToast.rest.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.dataForm.login.LoginRequestFrom;
import com.levelUpToast.levelUpToast.domain.dataForm.login.LoginResponseForm;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.service.login.loginServiceInterface.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final TokenManager tokenManager;


    @PostMapping("/login")
    public LoginResponseForm login(@RequestBody LoginRequestFrom form) {
        HashMap<String, String> data = new HashMap<>();
        try {
            Member loginMem = loginService.login(form.getId(), form.getPw());
            String token = tokenManager.createToken(loginMem.getManageSeq());
            data.put("token", token);
            return new LoginResponseForm(-1,"로그인 성공",data);
        } catch (LevelUpToastEx e) {
            data.put("token", null);
            return new LoginResponseForm(e.getERR_CODE(),e.getMessage(),data);
        } catch (Exception e){
            data.put("token", null);
            return new LoginResponseForm(-2, "예상치 못한 오류", data);
        }
    }
}
