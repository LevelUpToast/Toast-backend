package com.levelUpToast.levelUpToast.rest.login;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.login.LoginRequestFrom;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
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
    public ResponseForm<String> login(@RequestBody LoginRequestFrom form) {
        HashMap<String, String> data = new HashMap<>();
        try {
            Member loginMem = loginService.login(form.getId(), form.getPw());
            data.put("token", loginMem.getToken());
            log.info("[LoginController log] : 로그인 성공 manageSeq = {}, loginId = {}, name = {}", loginMem.getManageSeq(), loginMem.getId(), loginMem.getName());
            return new ResponseForm<>(-1, "로그인 성공", data);
        } catch (LevelUpToastEx e) {
            data.put("token", null);
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), data);
        }
    }

    @GetMapping("/logout/{token}")
    public ResponseForm<String> logout(@PathVariable String token) {
        try {
            loginService.logout(token);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
        return new ResponseForm<>(-1, "로그아웃 성공", null);
    }
}
