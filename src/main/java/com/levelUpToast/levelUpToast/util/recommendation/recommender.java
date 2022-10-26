package com.levelUpToast.levelUpToast.util.recommendation;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.ProductAdapter;
import com.levelUpToast.levelUpToast.domain.UseCase.util.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class recommender implements Recommendation {

    private final ProductRepository productRepository;

    private final ProductAdapter productAdapter;

    @Override
    public ArrayList<ProductListResponseForm> recommendedProducts() throws LevelUpToastEx {
        return (ArrayList<ProductListResponseForm>) productAdapter.changeProductList(
                productAdapter.sizeController(productRepository.findAllProduct())
        );
    }
}
