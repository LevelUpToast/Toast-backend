package com.levelUpToast.levelUpToast.product.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.service.productServiceInf.UpdateProduct;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.product.domain.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.product.domain.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateProductImpl implements UpdateProduct {

    private final ProductRepository productRepository;


    @Override
    public void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) throws LevelUpToastEx, SQLException {
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
