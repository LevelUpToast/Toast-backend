package com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface ProductAdapter {

    List<ResponseProductTable> sizeController(List<ResponseProductTable> responseProductTable);

    List<ProductListResponseForm> toProductList(List<ResponseProductTable> responseProductTable);

    ResponseProductTable toProductUUID(Long seq, DataBaseProductTable product) throws LevelUpToastEx;

    DataBaseProductTable toProductSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx;
}



