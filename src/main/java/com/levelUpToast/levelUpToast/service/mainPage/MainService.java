package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;

import java.util.List;
import java.util.Map;

public interface MainService {
    Map<String, Object> mainPage(Member loggedMem);

    String myFundingProducts();

    List<Product> listProcessing(List<Product> product);

    List<Product> getProduct();

    List<Product> getProductTag(Tag tag);
}
