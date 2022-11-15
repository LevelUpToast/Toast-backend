package com.levelUpToast.levelUpToast.product.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.service.productServiceInf.DeleteProduct;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductImpl implements DeleteProduct {
    private final ProductRepository productRepository;

    @Override
    public void deleteProduct(Long seq) throws LevelUpToastEx {
        productRepository.removeProductBySeq(seq);
    }
}
