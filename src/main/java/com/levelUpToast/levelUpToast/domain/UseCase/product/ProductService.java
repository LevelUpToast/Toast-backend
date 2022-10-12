package com.levelUpToast.levelUpToast.domain.UseCase.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface ProductService {
    Long registerProduct(ProductRequestForm form, Long member) throws LevelUpToastEx, SQLException;

    void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) throws LevelUpToastEx, SQLException;

    void deleteProduct(Long seq) throws LevelUpToastEx;

    Optional<ResponseProductTable> getProduct(Long seq) throws LevelUpToastEx, SQLException;

    ArrayList<ProductListResponseForm> recommendationProduct() throws LevelUpToastEx;


}
