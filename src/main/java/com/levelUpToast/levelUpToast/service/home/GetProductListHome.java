package com.levelUpToast.levelUpToast.service.home;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.home.ProductListHome;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.domain.UseCase.product.ProductAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("levelUpToast.provider.mainPage")
@RequiredArgsConstructor
public class GetProductListHome implements ProductListHome {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;
    /**
     * 순서없는 Product 반환하는 메소드
     * @return main 형식에 맞춰 10개의 리스트만 반환한다.
     */
    @Override
    public List<ProductListResponseForm> getHomeProduct() throws LevelUpToastEx {
        return productAdapter.changeProductList(
                productAdapter.sizeController(productRepository.findAllProduct())
        );
    }
}
