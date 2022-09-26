package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;


import java.util.Optional;

public interface UpdateProduct {
     void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form);
}
