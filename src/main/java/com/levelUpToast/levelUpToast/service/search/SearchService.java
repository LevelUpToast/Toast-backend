package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.model.product.Product;

import java.util.List;

public interface SearchService {
    List<Product> SearchProduct(String title, int index);
    List<Product> Keyword(List<Product> product, String title);
}
