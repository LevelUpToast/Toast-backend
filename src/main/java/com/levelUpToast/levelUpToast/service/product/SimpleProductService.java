package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Product 데이터 저장하는 메소드
     * @param form 요청받은 Body 내용을 받아 Product 형태로 만든다.
     * @param ManageSeq 제품 등록하는 판매자의 Seq 등록한다.
     * @return 데이터 폼을 만든 것을 Return 한다.
     */
    @Override
    public String addProduct(ProductRequestForm form, Long ManageSeq) {
        ResponseProductTable responseProductTable = new ResponseProductTable(
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
                new ResponseProductInfo(
                        form.getText(),
                        form.getProductImgUrl()),
                form.getBuyOption(),
                new Review(0, 0, 0, 0, 0)
        );
        productRepository.saveProduct(responseProductTable);
        return responseProductTable.getProductSeq().toString();
    }

    /**
     * 등록된 제품을 삭제하는 메소드
     * @param seq 삭제할 Product Seq 요청하면 삭제를 진행
     *            Return 존재하지 않는다.
     */
    @Override
    public void deleteProduct(Long seq) {
        productRepository.removeProductBySeq(seq);
    }

    /**
     * 특정 Product 데이터를 요청하고 Return 한다.
     *
     * @param seq 요청할 Seq 번호
     * @return 요청한 product 정보를 가져오고 넘겨준다.
     */
    @Override
    public Optional<ResponseProductTable> getProduct(Long seq) {
        return productRepository.findProductBySeq(seq);
    }


    /**
     * Product 상품 정보를 수정하는 요청 메소드
     * @param original 수정 대상의 Product 데이터를 받는다.
     * @param seq 수정할 Product seq
     * @param form 수정 내용을 담아 보낼 product
     */
    @Override
    public void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) {
        productRepository.updateProduct(seq, new ResponseProductTable(
                form.getTitle(),
                form.getInitialImgUrl(),
                form.getTag(),
                // 내부 데이터를 통해 처리
                new FundingInfo(
                        0,
                        form.getFinalAmount(),
                        form.getDeadline()),
                original.orElseThrow().getLike(),
                original.orElseThrow().getVendorSeq(),
                new ResponseProductInfo(
                        form.getText(),
                        form.getProductImgUrl()),
                form.getBuyOption(),
                original.orElseThrow().getReview()
        ));
    }

}
