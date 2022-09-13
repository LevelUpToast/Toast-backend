package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.model.product.Product;

import java.util.Optional;

public interface ProductService {
    Product registerProduct(ProductRequestForm form, Long member);
    void updateProduct(Optional<Product> original, Long seq, ProductRequestForm form);
    void deleteProduct(Long seq);
    Optional<Product> getProduct(Long seq);
}
