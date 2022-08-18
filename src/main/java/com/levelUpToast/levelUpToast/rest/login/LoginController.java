package com.levelUpToast.levelUpToast.rest.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.dataForm.login.LoginRequestFrom;
import com.levelUpToast.levelUpToast.domain.dataForm.login.LoginResponseForm;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.service.login.loginServiceInf.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
            data.put("token", loginMem.getToken());
            log.info("[LoginController log] : 로그인 성공 manageSeq = {}, loginId = {}, name = {}",loginMem.getManageSeq(),loginMem.getId(),loginMem.getName());
            return new LoginResponseForm(-1,"로그인 성공",data);
        } catch (LevelUpToastEx e) {
            data.put("token", null);
            return new LoginResponseForm(e.getERR_CODE(),e.getMessage(),data);
        }
    }

    @GetMapping("/logout/{token}")
    public LoginResponseForm logout(@PathVariable String token) {
        try {
            loginService.logout(token);
        } catch (LevelUpToastEx e) {
            return new LoginResponseForm(e.getERR_CODE(), e.getMessage(), null);
        }
        return new LoginResponseForm(-1, "로그아웃 성공", null);
    }
}
