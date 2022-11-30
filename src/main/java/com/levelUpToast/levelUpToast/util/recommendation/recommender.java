package com.levelUpToast.levelUpToast.util.recommendation;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.product.ProductAdapter;
import com.levelUpToast.levelUpToast.util.utilInf.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
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
        return (ArrayList<ProductListResponseForm>) productAdapter.toProductList(
                productAdapter.sizeController(productRepository.findAllProduct())
        );
    }
}
