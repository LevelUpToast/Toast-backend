package com.levelUpToast.levelUpToast.service.product;


import com.levelUpToast.levelUpToast.domain.UseCase.product.*;
import com.levelUpToast.levelUpToast.domain.UseCase.product.InformationProduct;
import com.levelUpToast.levelUpToast.domain.UseCase.util.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
    public String registerProduct(ProductRequestForm form, Long ManageSeq) {
        return simpleRegisterProduct.registerProduct(form, ManageSeq).getProductSeq().toString();
    }
    @Override
    public void deleteProduct(Long seq) {
        simpleDeleteProduct.deleteProduct(seq);
    }

    @Override
    public Optional<ResponseProductTable> getProduct(Long seq) {
        return simpleInformationProduct.getProduct(seq);
    }

    @Override
    public ArrayList<ProductListResponseForm> recommendationProduct() {
        return recommendation.recommendedProducts();
    }

    @Override
    public void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) {
        simpleUpdateProduct.updateProduct(original, seq, form);
    }

}
