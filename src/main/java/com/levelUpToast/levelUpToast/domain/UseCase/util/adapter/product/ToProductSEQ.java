package com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;

public interface ToProductSEQ {
    DataBaseProductTable toProductSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx;
}
