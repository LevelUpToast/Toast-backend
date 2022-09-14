package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MainService {


    String myFundingProducts();
    ArrayList<String> getBanner();

    List<ProductListResponseForm> getProduct();

    List<ProductListResponseForm> getProductTag(Tag tag);
}
