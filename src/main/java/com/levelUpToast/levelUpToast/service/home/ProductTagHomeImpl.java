package com.levelUpToast.levelUpToast.service.home;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.home.ProductTagHome;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product.ProductAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTagHomeImpl implements ProductTagHome {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    @Override
    public List<ProductListResponseForm> getProductTag(Tag tag) throws LevelUpToastEx {
        return productAdapter.toProductList(
                productAdapter.sizeController(productRepository.findProductByTag(tag))
        );
    }
}
