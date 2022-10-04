package com.levelUpToast.levelUpToast.domain.UseCase.util.recommendation;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.ArrayList;

public interface Recommendation {
    ArrayList<ProductListResponseForm> recommendedProducts();
}
