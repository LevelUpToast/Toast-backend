package com.levelUpToast.levelUpToast.domain.UseCase.home;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;

import java.util.ArrayList;
import java.util.List;

public interface HomeService {


    String myFundingProducts();

    ArrayList<String> getBanner();

    ArrayList<ProductListResponseForm> getProduct() throws LevelUpToastEx;

    List<ProductListResponseForm> getProductTag(Tag tag) throws LevelUpToastEx;
}
