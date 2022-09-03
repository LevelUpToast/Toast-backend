package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.dataForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.product.Product;

public interface ProductService {
    Product productRegister(ProductRequestForm form, Long member);
    void delete();
}
