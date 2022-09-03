package com.levelUpToast.levelUpToast.service.main;

import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMainService implements MainService{

    private final ProductRepository productRepository;

    @Value("${img.url.banner}")
    private String banner;

    @Override
    public Map<String, Object> mainPage(Member loggedMem) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("bannerImgUrl", banner);
        if (loggedMem == null)
            data.put("myFundingProducts", null); // 펀딩레포지토리 완성 이후 token을 통해 찾아서 넣을 예정
//        else{
//          //정보 회원 정보를 통해 불러와  myFundingProducts 넣어주기
//          data.put("myFundingProducts", loggedMem.....);
//        }
        data.put("recommendedProducts", new ArrayList<>(Collections.singletonList(productRepository.findAllProduct())));
        data.put("fruitProducts", productRepository.findProductByTag(Tag.FRUIT));
        data.put("vegetableProducts", productRepository.findProductByTag(Tag.VEGETABLE));

        return data;
    }
}
