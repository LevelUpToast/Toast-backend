package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface SearchService {
    List<ProductListResponseForm> SearchProduct(String title, int index);
    List<ResponseProductTable> Keyword(List<ResponseProductTable> responseProductTable, String title);
}
