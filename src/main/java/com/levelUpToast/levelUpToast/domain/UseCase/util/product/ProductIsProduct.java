package com.levelUpToast.levelUpToast.domain.UseCase.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface ProductIsProduct {
    void isProductSEQ(Long originalProductVendor, Long requestSeq) throws LevelUpToastEx;
}
