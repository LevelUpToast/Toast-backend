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

    /**
     * 순서없는 Product 반환하는 메소드
     *
     * @return main 형식에 맞춰 10개의 리스트만 반환한다.
     */
    @Override
    public ArrayList<ProductListResponseForm> getProduct() throws LevelUpToastEx {
        return recommendation.recommendedProducts();
    }

    /**
     * Product 태그가 포홤된 데이터를 가져온는 메소드
     *
     * @param tag Tag 포함된 데이터만 선택한다.
     * @return 요청된 Tag 갖고있는 Product Return 한다.
     */
    @Override
    public List<ProductListResponseForm> getProductTag(Tag tag) throws LevelUpToastEx {
        return simpleGetProductTag.getProductTag(tag);
    }

    /**
     * Home 화면에서 보여질 Banner 사진을 반환하는 메소드
     * @return ArrayList<String>형식으로 반환되며, List 안에는 이미지 파일명을 담고 있다.
     */
    @Override
    public ArrayList<String> getBanner() {
        return simpleGetBannerHome.getBanner();
    }


}
