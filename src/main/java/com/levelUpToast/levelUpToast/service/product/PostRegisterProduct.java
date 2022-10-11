package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.product.RegisterProduct;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostRegisterProduct implements RegisterProduct {
    private final ProductRepository productRepository;

    /**
     * Product 데이터 저장하는 메소드
     *
     * @param form      요청받은 Body 내용을 받아 Product 형태로 만든다.
     * @param ManageSeq 제품 등록하는 판매자의 Seq 등록한다.
     * @return 데이터 폼을 만든 것을 Return 한다.
     */
    @Override
    public ResponseProductTable registerProduct(ProductRequestForm form, Long ManageSeq) throws LevelUpToastEx, SQLException {
        log.info("manage Seq : {}",ManageSeq);
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
        return responseProductTable;
    }
}
