package com.levelUpToast.levelUpToast.domain.UseCase.home;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface ProductListHome {
    List<ProductListResponseForm> getHomeProduct() throws LevelUpToastEx;
}
