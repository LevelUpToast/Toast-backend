package com.levelUpToast.levelUpToast.product.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.service.productServiceInf.RegisterProduct;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.product.domain.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.product.domain.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.product.domain.data.reviwe.Review;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterProductImpl implements RegisterProduct {
    private final ProductRepository productRepository;

    @Override
    public ResponseProductTable registerProductInfo(ProductRequestForm form, Long ManageSeq) throws LevelUpToastEx, SQLException {
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
