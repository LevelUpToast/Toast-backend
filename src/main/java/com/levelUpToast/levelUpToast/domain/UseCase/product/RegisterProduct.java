package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;


public interface RegisterProduct {
    ResponseProductTable registerProduct(ProductRequestForm form, Long ManageSeq);
}
