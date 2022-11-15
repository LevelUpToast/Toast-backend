package com.levelUpToast.levelUpToast.product.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.service.productServiceInf.InformationProduct;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InformationProductImpl implements InformationProduct {

    private final ProductRepository productRepository;


    public Optional<ResponseProductTable> getProductByInfo(Long seq) throws LevelUpToastEx, SQLException {
        return productRepository.findProductBySeq(seq);
    }
}
