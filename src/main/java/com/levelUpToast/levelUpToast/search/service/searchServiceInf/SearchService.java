package com.levelUpToast.levelUpToast.search.service.searchServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;
import java.util.Map;

public interface SearchService {
    Map<String, Object> SearchProduct(String title, int index) throws LevelUpToastEx;

}
