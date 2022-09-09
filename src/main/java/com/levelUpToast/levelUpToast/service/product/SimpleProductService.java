package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.model.product.productinfo.ProductInfo;
import com.levelUpToast.levelUpToast.domain.model.product.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final ProductRepository productRepository;

    //상품추가
    @Override
    public Product registerProduct(ProductRequestForm form, Long ManageSeq) {
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
    public void deleteProduct(Long seq) {
        productRepository.removeProductBySeq(seq);
        log.info("[ProductService log] : 제품 삭제 완료, 제품Seq = {}", seq);
    }

    @Override
    public Map<String, Object> getProduct(Long seq){
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("Product", productRepository.findProductBySeq(seq));
        return data;
    }

    // 상품 업데이트
    @Override
    public int updateProduct(Long seq, Product product){
        try {
            productRepository.updateProduct(seq, product);
        }catch (Exception e){
            return 999;
        }
        return -1;
    }
}
