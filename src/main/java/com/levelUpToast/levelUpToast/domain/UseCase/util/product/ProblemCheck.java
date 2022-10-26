package com.levelUpToast.levelUpToast.domain.UseCase.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;

import java.sql.SQLException;
import java.util.Optional;

public interface ProblemCheck {

    void isProductSEQ(Long originalProductVendor, Long requestSeq) throws LevelUpToastEx;

    Optional<ResponseProductTable> isProduct(Long productSeq) throws LevelUpToastEx, SQLException;
}
