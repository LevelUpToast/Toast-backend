package com.levelUpToast.levelUpToast.home.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.home.repository.HomeRepository;
import com.levelUpToast.levelUpToast.home.service.homeServiceInf.HomeService;
import com.levelUpToast.levelUpToast.member.domain.Member;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.check.UserIntegrityVerification;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.product.ProductAdapter;
import com.levelUpToast.levelUpToast.util.utilInf.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.product.domain.data.tag.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleHomeService implements HomeService {
    private final UserIntegrityVerification simpleUserIntegrityVerification;
    private final HomeRepository homeRepository;
    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    /**
     * Controller 요청 받은 데이터를 처리하는 메소드
     *
     * @param token 사용자의 Member 정보를 호출받아 펀딩정보를 가져온다.
     * @return 메인화면에 뿌려질 데이터를 Return
     */
    public Map<String, Object> homeService(String token) throws LevelUpToastEx {
        Member member = simpleUserIntegrityVerification.isMember(token);
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("bannerImgUrl", getBanner());
        if (member != null) data.put("myFundingProducts", myFundingProducts()); // 펀딩레포지토리 완성 이후 Token 통해 찾아서 넣을 예정
        else data.put("myFundingProducts", null);
        data.put("recommendedProducts", getProduct());
        data.put("fruitProducts", getProductTag(Tag.FRUIT));
        data.put("vegetableProducts", getProductTag(Tag.VEGETABLE));

        return data;
    }

    /**
     * Home 화면에서 보여질 Banner 사진을 반환하는 메소드
     *
     * @return ArrayList<String>형식으로 반환되며, List 안에는 이미지 파일명을 담고 있다.
     */
    private ArrayList<String> getBanner() {
        return homeRepository.getBanner();
    }

    /**
     * Product 펀딩정보를 불러오는 메소드
     *
     * @return 사용자에 구매한 펀딩 정보를 Return 메소드
     */
    private String myFundingProducts() {
        return null;
    }

    /**
     * 순서없는 Product 반환하는 메소드
     *
     * @return main 형식에 맞춰 10개의 리스트만 반환한다.
     */
    private List<ProductListResponseForm> getProduct() throws LevelUpToastEx {
        return productAdapter.toProductList(productAdapter.sizeController(productRepository.findAllProduct()));
    }

    /**
     * Product 태그가 포홤된 데이터를 가져온는 메소드
     *
     * @param tag Tag 포함된 데이터만 선택한다.
     * @return 요청된 Tag 갖고있는 Product Return 한다.
     */
    private List<ProductListResponseForm> getProductTag(Tag tag) throws LevelUpToastEx {
        return productAdapter.toProductList(productAdapter.sizeController(productRepository.findProductByTag(tag)));
    }


}
