package com.levelUpToast.levelUpToast.service.product;


import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.product.*;
import com.levelUpToast.levelUpToast.domain.UseCase.product.InformationProduct;
import com.levelUpToast.levelUpToast.domain.UseCase.util.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {
    private final DeleteProduct simpleDeleteProduct;
    private final InformationProduct simpleInformationProduct;
    private final UpdateProduct simpleUpdateProduct;
    private final RegisterProduct simpleRegisterProduct;
    private final Recommendation recommendation;


    @Override
    public Long registerProduct(ProductRequestForm form, Long ManageSeq) throws LevelUpToastEx, SQLException {
        return simpleRegisterProduct.registerProduct(form, ManageSeq).getProductSeq();
    }
    @Override
    public void deleteProduct(Long seq) throws LevelUpToastEx {
        simpleDeleteProduct.deleteProduct(seq);
    }

    @Override
    public Optional<ResponseProductTable> getProduct(Long seq) throws LevelUpToastEx, SQLException {
        return simpleInformationProduct.getProduct(seq);
    }

    @Override
    public ArrayList<ProductListResponseForm> recommendationProduct() throws LevelUpToastEx {
        return recommendation.recommendedProducts();
    }

    @Override
    public void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) throws LevelUpToastEx, SQLException {
        simpleUpdateProduct.updateProduct(original, seq, form);
    }

}
