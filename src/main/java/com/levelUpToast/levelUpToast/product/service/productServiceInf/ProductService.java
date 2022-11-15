package com.levelUpToast.levelUpToast.product.service.productServiceInf;

import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductDeleteRequestForm;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductRequestForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Long registerProduct(ProductRequestForm form) throws LevelUpToastEx, SQLException;

    void updateProduct(Long seq, ProductRequestForm form) throws LevelUpToastEx, SQLException;

    void deleteProduct(ProductDeleteRequestForm form) throws LevelUpToastEx, SQLException;

    Map<String, Object> getProduct(Long seq) throws LevelUpToastEx, SQLException;

    ArrayList<ProductListResponseForm> recommendationProduct() throws LevelUpToastEx;


}
