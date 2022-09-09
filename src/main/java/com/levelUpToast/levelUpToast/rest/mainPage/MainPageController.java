package com.levelUpToast.levelUpToast.rest.mainPage;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.dataForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.mainPage.SimpleMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor

public class MainPageController {
    private final TokenManager tokenManager;

    private final MemberRepository memberRepository;

    private final SimpleMainService simpleMainService;

    @GetMapping("/main")
    public ResponseForm<Object> mainPage() {
        log.info("[MainPageController log] : 비회원 메인 페이지 요청");
        return new ResponseForm<>(-1, "로그인 미완료된 MainPage", simpleMainService.mainPage(null));
    }

    @GetMapping("/main/{token}")
    public ResponseForm<Object> loggedMainPage(@PathVariable("token") String token) {
        try {
            Optional<Member> findMem = memberRepository.findByManageSeq(tokenManager.findMemberSeqByToken(token));
            if (findMem.isEmpty()) return new ResponseForm<>(131, "토큰은 있는데 회원은 없는 서버에러", null);

            log.info("[MainPageController log] : 로그인한 회원 메인 페이지 요청");
            return new ResponseForm<>(-1, "로그인 완료된 MainPage", simpleMainService.mainPage(null));
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }

    }
}
