package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;

import java.util.Optional;

public interface InformationProduct {
    Optional<ResponseProductTable> getProduct(Long seq);
}
