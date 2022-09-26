package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;

import java.util.Optional;

public interface ProductService {
    String registerProduct(ProductRequestForm form, Long member);

    void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form);

    void deleteProduct(Long seq);

    Optional<ResponseProductTable> getProduct(Long seq);
}
