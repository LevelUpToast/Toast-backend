package com.levelUpToast.levelUpToast.product.service.productServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;


import java.sql.SQLException;
import java.util.Optional;

public interface UpdateProduct {
     void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) throws LevelUpToastEx, SQLException;
}
