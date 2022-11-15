package com.levelUpToast.levelUpToast.search.service.searchServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface SearchService {
    List<ProductListResponseForm> SearchProduct(String title, int index) throws LevelUpToastEx;

}
