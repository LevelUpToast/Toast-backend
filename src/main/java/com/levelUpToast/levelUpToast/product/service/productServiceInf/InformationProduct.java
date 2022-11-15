package com.levelUpToast.levelUpToast.product.service.productServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;

import java.sql.SQLException;
import java.util.Optional;

public interface InformationProduct {
    Optional<ResponseProductTable> getProductByInfo(Long seq) throws LevelUpToastEx, SQLException;
}
