package com.levelUpToast.levelUpToast.function.productAdapter;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface ProductAdapter {

    List<Product> sizeController(List<Product> product);

    List<Product> sizeController(List<Product> product, int index);

    List<ProductListResponseForm> changeProductList(List<Product> product);

    List<String> extractImgUUID(List<Long> imgSeq);

    List<Long> extractImgSeq(List<String> imgUUIDList);

}



