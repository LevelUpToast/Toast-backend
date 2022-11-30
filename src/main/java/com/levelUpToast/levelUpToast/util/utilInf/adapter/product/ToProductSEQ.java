package com.levelUpToast.levelUpToast.util.utilInf.adapter.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.domain.DataBaseProductTable;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;

public interface ToProductSEQ {
    DataBaseProductTable toProductSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx;
}
