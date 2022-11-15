package com.levelUpToast.levelUpToast.util.utilInf.recommendation;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;

import java.util.ArrayList;

public interface Recommendation {
    ArrayList<ProductListResponseForm> recommendedProducts() throws LevelUpToastEx;
}
