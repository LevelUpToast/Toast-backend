package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.UseCase.product.UpdateProduct;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateInformationProduct implements UpdateProduct {

    private final ProductRepository productRepository;

    /**
     * Product 상품 정보를 수정하는 요청 메소드
     *
     * @param original 수정 대상의 Product 데이터를 받는다.
     * @param seq      수정할 Product seq
     * @param form     수정 내용을 담아 보낼 product
     */
    @Override
    public void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) {
        productRepository.updateProduct(seq, new ResponseProductTable(
                form.getTitle(),
                form.getInitialImgUrl(),
                form.getTag(),
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
