package com.levelUpToast.levelUpToast.product.service.productServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface DeleteProduct {
    void deleteProduct(Long seq) throws LevelUpToastEx;
}
