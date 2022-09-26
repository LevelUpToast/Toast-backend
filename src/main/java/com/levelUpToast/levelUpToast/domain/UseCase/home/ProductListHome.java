package com.levelUpToast.levelUpToast.domain.UseCase.home;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.List;

public interface ProductListHome {
    List<ProductListResponseForm> getHomeProduct();
}
