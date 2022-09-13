package com.levelUpToast.levelUpToast.function.productAdapter;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.productDataForm.ProductList;

import java.util.List;

public interface ProductAdapter {

    List<Product> sizeController(List<Product> product);

    List<Product> sizeController(List<Product> product, int index);

    List<ProductList> productChange(List<Product> product);

    List<String> extractImgUUID(List<Long> imgSeq);

}



