package com.levelUpToast.levelUpToast.service.home;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.home.BannerHome;
import com.levelUpToast.levelUpToast.domain.UseCase.home.ProductListHome;
import com.levelUpToast.levelUpToast.domain.UseCase.home.ProductTagHome;
import com.levelUpToast.levelUpToast.domain.UseCase.home.HomeService;
import com.levelUpToast.levelUpToast.domain.UseCase.util.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleHomeService implements HomeService {

    private final BannerHome simpleGetBannerHome;

    private final ProductTagHome simpleGetProductTag;

    private final ProductListHome simpleHomeProductList;

    private final Recommendation recommendation;

    /**
     * Product 펀딩정보를 불러오는 메소드
     *
     * @return 사용자에 구매한 펀딩 정보를 Return 메소드
     */
    @Override
    public String myFundingProducts() {
        return null;
    }

    @Override
    public ArrayList<ProductListResponseForm> getProduct() throws LevelUpToastEx {
        return recommendation.recommendedProducts();
    }

    @Override
    public List<ProductListResponseForm> getProductTag(Tag tag) throws LevelUpToastEx {
        return simpleGetProductTag.getProductTag(tag);
    }

    @Override
    public ArrayList<String> getBanner() {
        return simpleGetBannerHome.getBanner();
    }


}
