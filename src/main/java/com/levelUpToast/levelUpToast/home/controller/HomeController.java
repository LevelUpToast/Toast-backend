package com.levelUpToast.levelUpToast.home.controller;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.home.service.homeServiceInf.HomeService;
import com.levelUpToast.levelUpToast.bodyForm.responseForm.ResponseForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class HomeController {
    private final HomeService simpleHomeService;

    /**
     * 클라이언트로부터 메인 정보를 요청 받으면 처리하는 Controller
     * 배너, 추천, 야채, 과일 정보를 보내준다.
     * @return 로그인이 되지않은경우 펀딩 정보를 제외하고 정보를 보낸다.
     */
    @GetMapping("/home")
    public ResponseForm<Object> mainPage() throws LevelUpToastEx {
        log.info("[MainPageController log] : 비회원 메인 페이지 요청");
        return new ResponseForm<>(-1, "로그인 미완료된 MainPage", simpleHomeService.homeService(null));
    }

    /**
     * 클라이언트로부터 메인 정보를 요청 받으면 처리하는 Controller
     * 배너, 펀딩 정보,추천, 야채, 과일 정보를 보내준다.
     *
     * @return 사용자가 구매한 펀딩이 있다면, 펀딩정보를 담아서 보내준다.
     */
    @GetMapping("/home/{token}")
    public ResponseForm<Object> loggedMainPage(@PathVariable("token") String token) {
        try {
            log.info("[MainPageController log] : 로그인한 회원 메인 페이지 요청");
            return new ResponseForm<>(-1, "로그인 완료된 MainPage", simpleHomeService.homeService(token));
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }

    }
}
