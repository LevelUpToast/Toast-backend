package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.product.Product;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MainService {
    Map<String, Object> mainPage(Member loggedMem);

    String myFundingProducts();

    List<Product> getProduct();

    List<Product> getProductTag(Tag tag);
}
