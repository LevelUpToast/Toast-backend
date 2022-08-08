package com.levelUpToast.levelUpToast.rest.login;

import com.levelUpToast.levelUpToast.domain.dataForm.login.LoginRequestFrom;
import com.levelUpToast.levelUpToast.domain.dataForm.login.LoginResponseForm;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.service.login.loginServiceInterface.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    public LoginResponseForm login(@RequestBody LoginRequestFrom form) {
        HashMap<String, String> data = new HashMap<>();
        try {
            Member loginMem = loginService.login(form.getId(), form.getPw());
            data.put("token", "123-123-123-213");
            return new LoginResponseForm(-1,"로그인 성공",data);
        } catch (Exception e) {
            data.put("token", null);
            return new LoginResponseForm(-1,e.getMessage(),data);
        }
    }
}
