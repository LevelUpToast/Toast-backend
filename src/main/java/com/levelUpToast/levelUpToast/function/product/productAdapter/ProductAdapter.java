package com.levelUpToast.levelUpToast.function.product.productAdapter;

import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface ProductAdapter {

    List<ResponseProductTable> sizeController(List<ResponseProductTable> responseProductTable);

    List<ResponseProductTable> sizeController(List<ResponseProductTable> responseProductTable, int index);

    List<ProductListResponseForm> changeProductList(List<ResponseProductTable> responseProductTable);

}



