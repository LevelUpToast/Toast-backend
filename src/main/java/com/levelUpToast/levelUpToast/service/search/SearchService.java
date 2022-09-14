package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface SearchService {
    List<ProductListResponseForm> SearchProduct(String title, int index);
    List<Product> Keyword(List<Product> product, String title);
}
