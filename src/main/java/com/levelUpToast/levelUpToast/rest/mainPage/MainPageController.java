package com.levelUpToast.levelUpToast.rest.mainPage;

import com.levelUpToast.levelUpToast.domain.dataForm.mainPage.MainPageResponseForm;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;
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

    private final ProductRepository productRepository;

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
        return new MainPageResponseForm(-1, "로그인 미완료된 MainPage", data);

    }

    @GetMapping("/main/{token}")
    public MainPageResponseForm loggedMainPage(@PathVariable("token")String token) {
        return null;
    }
}
