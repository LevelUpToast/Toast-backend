package com.levelUpToast.levelUpToast.search.service.searchServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface SearchProduct {
    List<ProductListResponseForm> Search(String inputKeyword, int index) throws LevelUpToastEx;
}
