package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;

import java.sql.SQLException;


public interface RegisterProduct {
    ResponseProductTable registerProduct(ProductRequestForm form, Long ManageSeq) throws LevelUpToastEx, SQLException;
}
