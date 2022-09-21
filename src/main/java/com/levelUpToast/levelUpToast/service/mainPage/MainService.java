package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;

import java.util.ArrayList;
import java.util.List;

public interface MainService {


    String myFundingProducts();
    ArrayList<String> getBanner();

    List<ProductListResponseForm> getProduct();

    List<ProductListResponseForm> getProductTag(Tag tag);
}
