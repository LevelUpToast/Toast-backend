package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.mainRepository.MainRepository;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.function.product.productAdapter.ProductAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMainService implements MainService {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;
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
    public List<ProductListResponseForm> getProduct() {
        return productAdapter.changeProductList(
                productAdapter.sizeController(productRepository.findAllProduct())
        );
    }

    /**
     * Product 태그가 포홤된 데이터를 가져온는 메소드
     * @param tag Tag 포함된 데이터만 선택한다.
     * @return 요청된 Tag 갖고있는 Product Return 한다.
     */
    @Override
    public List<ProductListResponseForm> getProductTag(Tag tag) {
        return productAdapter.changeProductList(
                productAdapter.sizeController(productRepository.findProductByTag(tag))
        );
    }

    @Override
    public ArrayList<String> getBanner(){
        return  mainRepository.getBanner();
    }

}
