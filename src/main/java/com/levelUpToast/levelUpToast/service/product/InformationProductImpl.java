package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.product.InformationProduct;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
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
