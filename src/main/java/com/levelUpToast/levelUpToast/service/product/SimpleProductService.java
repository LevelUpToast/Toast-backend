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

    /**
     * Product 데이터 저장하는 메소드
     * @param form 요청받은 Body 내용을 받아 Product 형태로 만든다.
     * @param ManageSeq 제품 등록하는 판매자의 Seq를 등록한다.
     * @return 데이터 폼을 만든 것을 Return한다.
     */
    @Override
    public Product registerProduct(ProductRequestForm form, Long ManageSeq) {
        Product product = new Product(
                form.getTitle(),
                form.getInitialImgUrl(),
                form.getTag(),
                // 내부 데이터를 통해 처리
                new FundingInfo(
                        0,
                        form.getFinalAmount(),
                        form.getDeadline()),
                0,
                ManageSeq,
                new ProductInfo(
                        form.getText(),
                        form.getProductImgUrl()),
                form.getBuyOption(), // -> 현재 문제점
                new Review(0, 0, 0, 0, 0)
        );
        productRepository.saveProduct(product);
        return product;
    }

    /**
     * 등록된 제품을 삭제하는 메소드
     * @param seq 삭제할 Product Seq를 요청하면 삭제를 진행
     *            Return은 존재하지 않는다.
     */
    @Override
    public void deleteProduct(Long seq) {
        productRepository.removeProductBySeq(seq);
        log.info("[ProductService log] : 제품 삭제 완료, 제품Seq = {}", seq);
    }

    /**
     * 특정 Product 데이터를 요청하고 Return 한다.
     * @param seq 요청할 Seq 번호
     * @return 요청한 product 정보를 가져오고 넘겨준다.
     */
    @Override
    public Map<String, Object> getProduct(Long seq) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("Product", productRepository.findProductBySeq(seq));
        return data;
    }

    /**
     * Product 상품 정보를 수정하는 요청 메소드
     * @param seq 수정할 Product seq
     * @param product 수정 내용을 담아 보낼 product
     * @return 요청사항에 따른 return 코드 반환
     */
    @Override
    public int updateProduct(Long seq, Product product) {
        try {
            productRepository.updateProduct(seq, product);
        } catch (Exception e) {
            return 999;
        }
        return 999;
    }
}
