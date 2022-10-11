package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;

import java.sql.SQLException;
import java.util.Optional;

public interface InformationProduct {
    Optional<ResponseProductTable> getProduct(Long seq) throws LevelUpToastEx, SQLException;
}
