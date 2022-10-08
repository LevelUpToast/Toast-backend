package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface DeleteProduct {
    void deleteProduct(Long seq) throws LevelUpToastEx;
}
