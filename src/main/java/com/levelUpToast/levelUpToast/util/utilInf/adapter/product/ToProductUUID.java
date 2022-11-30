package com.levelUpToast.levelUpToast.util.utilInf.adapter.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.domain.DataBaseProductTable;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;

public interface ToProductUUID {
    ResponseProductTable toProductUUID(Long seq, DataBaseProductTable product) throws LevelUpToastEx;
}
