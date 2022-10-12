package com.levelUpToast.levelUpToast.domain.UseCase.search;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface SearchProduct {
    List<ProductListResponseForm> Search(String inputKeyword, int index) throws LevelUpToastEx;
}
