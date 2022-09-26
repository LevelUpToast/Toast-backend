package com.levelUpToast.levelUpToast.domain.UseCase.home;

import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;

import java.util.List;

public interface ProductTagHome {
    List<ProductListResponseForm> getProductTag(Tag tag);
}
