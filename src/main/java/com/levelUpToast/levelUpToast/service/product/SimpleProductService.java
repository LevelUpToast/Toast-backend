package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.dataForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.dataForm.product.ProductResponseForm;
import com.levelUpToast.levelUpToast.domain.product.Product;
import com.levelUpToast.levelUpToast.domain.product.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.product.productinfo.ProductInfo;
import com.levelUpToast.levelUpToast.domain.product.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final ProductRepository productRepository;

    //상품추가
    @Override
    public Product productRegister(ProductRequestForm form, Long ManageSeq) {
        Product product = new Product(form.getTitle(), form.getInitialImgUrl(), form.getTag(),
                // 내부 데이터를 통해 처리
                new FundingInfo(0,
                        form.getFinalAmount(),
                        form.getDeadline()),
                        0,
                        ManageSeq,
                        new ProductInfo(form.getText(), form.getProductImgUrl()), form.getBuyOption(), // -> 현재 문제점
                        new Review(0, 0, 0, 0, 0) // 새로운 상품 등록으로 0 초기화
        );
        productRepository.saveProduct(product);

        return product;
    }

    //상품 취소
    @Override
    public void delete() {

    }


    // 상품 업데이트


}