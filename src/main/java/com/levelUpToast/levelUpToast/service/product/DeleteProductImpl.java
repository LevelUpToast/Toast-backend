package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.product.DeleteProduct;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
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
