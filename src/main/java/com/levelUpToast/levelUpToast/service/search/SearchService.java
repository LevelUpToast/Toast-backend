package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.ProductList;

import java.util.List;

public interface SearchService {
    List<ProductList> SearchProduct(String title, int index);
    List<Product> Keyword(List<Product> product, String title);
}
