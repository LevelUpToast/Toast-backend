package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.productDataForm.ProductList;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;

import java.util.List;
import java.util.Map;

public interface MainService {
    Map<String, Object> mainPage(Member loggedMem);

    String myFundingProducts();

    List<ProductList> getProduct();

    List<ProductList> getProductTag(Tag tag);
}
