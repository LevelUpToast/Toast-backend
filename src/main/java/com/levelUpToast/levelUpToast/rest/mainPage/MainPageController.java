package com.levelUpToast.levelUpToast.rest.mainPage;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.dataForm.mainPage.MainPageResponseForm;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.product.Product;
import com.levelUpToast.levelUpToast.domain.product.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final TokenManager tokenManager;

    @Value("${img.url.banner}")
    private String banner;


    @GetMapping("/main")
    public MainPageResponseForm mainPage() {

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("bannerImgUrl", banner);
        data.put("myFundingProducts", null);
        data.put("recommendedProducts", new ArrayList<>(Arrays.asList(productRepository.findAllProduct())));
        data.put("fruitProducts", productRepository.findProductByTag(Tag.FRUIT));
        data.put("vegetableProducts", productRepository.findProductByTag(Tag.VEGETABLE));

        log.info("[MainPageController log] : 비회원 메인 페이지 요청");
        return new MainPageResponseForm(-1, "로그인 미완료된 MainPage", data);

    }

    @GetMapping("/main/{token}")
    public MainPageResponseForm loggedMainPage(@PathVariable("token")String token) {
        // 현재 상품 결재를 통한 펀딩한 상품이 존재하지 않기 때문에 보류

        try{

            Optional<Member> findMem = memberRepository.findByManageSeq(tokenManager.findMemberSeqByToken(token));
            if(findMem.isEmpty()){
                return new MainPageResponseForm(9999, "토큰은 있는데 회원은 없는 서버에러", null);
            }
            Member loggedMem = findMem.get(); // loggedMem의 Seq를 통해 펀딩 레포지토리 값 넣을 예정

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("bannerImgUrl", banner);
            data.put("myFundingProducts", null); // 펀딩레포지토리 완성 이후 token을 통해 찾아서 넣을 예정
            data.put("recommendedProducts", new ArrayList<>(Arrays.asList(productRepository.findAllProduct())));
            data.put("fruitProducts", productRepository.findProductByTag(Tag.FRUIT));
            data.put("vegetableProducts", productRepository.findProductByTag(Tag.VEGETABLE));

            log.info("[MainPageController log] : 로그인한 회원 메인 페이지 요청");
            return new MainPageResponseForm(-1, "로그인 완료된 MainPage", data);

        }catch (LevelUpToastEx e){
            return new MainPageResponseForm(e.getERR_CODE(), e.getMessage(), null);
        }

    }
}
