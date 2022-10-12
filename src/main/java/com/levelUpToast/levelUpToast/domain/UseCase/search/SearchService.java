package com.levelUpToast.levelUpToast.domain.UseCase.search;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface SearchService {
    List<ProductListResponseForm> SearchProduct(String title, int index) throws LevelUpToastEx;

}
