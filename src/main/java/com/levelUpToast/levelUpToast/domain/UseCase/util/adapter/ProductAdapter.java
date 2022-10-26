package com.levelUpToast.levelUpToast.domain.UseCase.util.adapter;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface ProductAdapter {

    List<ResponseProductTable> sizeController(List<ResponseProductTable> responseProductTable);

    List<ResponseProductTable> sizeController(List<ResponseProductTable> responseProductTable, int index);

    List<ProductListResponseForm> changeProductList(List<ResponseProductTable> responseProductTable);

    ResponseProductTable changeImgToUUID(Long seq, DataBaseProductTable product) throws LevelUpToastEx;

    DataBaseProductTable changeImgToSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx;
}



