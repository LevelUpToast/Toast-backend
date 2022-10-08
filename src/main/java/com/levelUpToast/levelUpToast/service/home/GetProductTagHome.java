package com.levelUpToast.levelUpToast.service.home;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.home.ProductTagHome;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.domain.UseCase.product.ProductAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductTagHome implements ProductTagHome {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;
    /**
     * Product 태그가 포홤된 데이터를 가져온는 메소드
     * @param tag Tag 포함된 데이터만 선택한다.
     * @return 요청된 Tag 갖고있는 Product Return 한다.
     */
    @Override
    public List<ProductListResponseForm> getProductTag(Tag tag) throws LevelUpToastEx {
        return productAdapter.changeProductList(
                productAdapter.sizeController(productRepository.findProductByTag(tag))
        );
    }
}
