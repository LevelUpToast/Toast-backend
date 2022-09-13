package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.productDataForm.ProductList;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.mainRepository.MainRepository;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.function.productAdapter.ProductAdapter;
import com.levelUpToast.levelUpToast.function.productAdapter.SimpleProductAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMainService implements MainService {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter ;
    private final MainRepository mainRepository;

    /**
     * Product 펀딩정보를 불러오는 메소드
     * @return 사용자에 구매한 펀딩 정보를 Return 메소드
     */
    @Override
    public String myFundingProducts() {
        return null;
    }

    /**
     * 순서없는 Product 반환하는 메소드
     * @return main 형식에 맞춰 10개의 리스트만 반환한다.
     */
    @Override
    public List<ProductList> getProduct() {
        return productAdapter.productChange(
                productAdapter.sizeController(productRepository.findAllProduct())
        );
    }

    /**
     * Product 태그가 포홤된 데이터를 가져온는 메소드
     * @param tag Tag 포함된 데이터만 선택한다.
     * @return 요청된 Tag 갖고있는 Product Return 한다.
     */
    @Override
    public List<ProductList> getProductTag(Tag tag) {
        return productAdapter.productChange(
                productAdapter.sizeController(productRepository.findProductByTag(tag))
        );
    }

    /**
     * Controller 요청 받은 데이터를 처리하는 메소드
     * @param loggedMem 사용자의 Member 정보를 호출받아 펀딩정보를 가져온다.
     * @return 메인화면에 뿌려질 데이터를 Return
     */
    @Override
    public Map<String, Object> mainPage(Member loggedMem) {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("bannerImgUrl", mainRepository.getBanner());
        if (loggedMem != null) data.put("myFundingProducts", myFundingProducts()); // 펀딩레포지토리 완성 이후 token을 통해 찾아서 넣을 예정
        else data.put("myFundingProducts", null);
        data.put("recommendedProducts", getProduct());
        data.put("fruitProducts", getProductTag(Tag.FRUIT));
        data.put("vegetableProducts", getProductTag(Tag.VEGETABLE));

        return data;
    }


}
