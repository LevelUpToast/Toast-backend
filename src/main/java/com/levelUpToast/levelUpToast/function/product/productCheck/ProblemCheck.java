package com.levelUpToast.levelUpToast.function.product.productCheck;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;

import java.util.Optional;

public interface ProblemCheck {

    void checkProductSEQ(Long originalProductVendor, Long requestSeq) throws LevelUpToastEx;

    Optional<ResponseProductTable> checkProduct(Long productSeq) throws LevelUpToastEx;
}
