package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.dataForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.product.Product;

import java.util.Map;

public interface ProductService {
    Product registerProduct(ProductRequestForm form, Long member);

    void deleteProduct(Long seq);
    int updateProduct(Long seq, Product product);
    Map<String, Object> getProduct(Long seq);
}
