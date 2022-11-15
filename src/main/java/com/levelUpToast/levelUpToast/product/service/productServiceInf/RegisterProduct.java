package com.levelUpToast.levelUpToast.product.service.productServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;

import java.sql.SQLException;


public interface RegisterProduct {
    ResponseProductTable registerProductInfo(ProductRequestForm form, Long ManageSeq) throws LevelUpToastEx, SQLException;
}
