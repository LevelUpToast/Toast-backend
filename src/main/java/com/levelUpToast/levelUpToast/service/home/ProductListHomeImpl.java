package com.levelUpToast.levelUpToast.service.home;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.home.ProductListHome;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product.ProductAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("levelUpToast.provider.mainPage")
@RequiredArgsConstructor
public class ProductListHomeImpl implements ProductListHome {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    @Override
    public List<ProductListResponseForm> getHomeProduct() throws LevelUpToastEx {
        return productAdapter.toProductList(
                productAdapter.sizeController(productRepository.findAllProduct())
        );
    }
}
