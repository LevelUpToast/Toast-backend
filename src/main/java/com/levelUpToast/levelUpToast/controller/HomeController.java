package com.levelUpToast.levelUpToast.controller;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import com.levelUpToast.levelUpToast.util.member.SimpleMemberCheck;
import com.levelUpToast.levelUpToast.service.home.SimpleHomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
 class HomeController {
    private final SimpleMemberCheck simpleMemberCheck;
    private final SimpleHomeService mainService;

    /**
     * Controller 요청 받은 데이터를 처리하는 메소드
     * @param loggedMem 사용자의 Member 정보를 호출받아 펀딩정보를 가져온다.
     * @return 메인화면에 뿌려질 데이터를 Return
     */
    private Map<String, Object> mainPage(Member loggedMem) {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("bannerImgUrl", mainService.getBanner());
        if (loggedMem != null) data.put("myFundingProducts", mainService.myFundingProducts()); // 펀딩레포지토리 완성 이후 Token 통해 찾아서 넣을 예정
        else data.put("myFundingProducts", null);
        data.put("recommendedProducts", mainService.getProduct());
        data.put("fruitProducts", mainService.getProductTag(Tag.FRUIT));
        data.put("vegetableProducts", mainService.getProductTag(Tag.VEGETABLE));

        return data;
    }

    /**
     * 클라이언트로부터 메인 정보를 요청 받으면 처리하는 Controller
     * 배너, 추천, 야채, 과일 정보를 보내준다.
     * @return 로그인이 되지않은경우 펀딩 정보를 제외하고 정보를 보낸다.
     */
    @GetMapping("/main")
    public ResponseForm<Object> mainPage() {
        log.info("[MainPageController log] : 비회원 메인 페이지 요청");
        return new ResponseForm<>(-1, "로그인 미완료된 MainPage", mainPage(null));
    }

    /**
     * 클라이언트로부터 메인 정보를 요청 받으면 처리하는 Controller
     * 배너, 펀딩 정보,추천, 야채, 과일 정보를 보내준다.
     * @return 사용자가 구매한 펀딩이 있다면, 펀딩정보를 담아서 보내준다.
     */
    @GetMapping("/main/{token}")
    public ResponseForm<Object> loggedMainPage(@PathVariable("token") String token) {
        try {
            Member member = simpleMemberCheck.checkMember(token);
            log.info("[MainPageController log] : 로그인한 회원 메인 페이지 요청");
            return new ResponseForm<>(-1, "로그인 완료된 MainPage", mainPage(member));
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }

    }
}