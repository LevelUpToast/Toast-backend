package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;

import java.util.Optional;

public interface ProductService {
    String addProduct(ProductRequestForm form, Long member);
    void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form);
    void deleteProduct(Long seq);
    Optional<ResponseProductTable> getProduct(Long seq);
}
