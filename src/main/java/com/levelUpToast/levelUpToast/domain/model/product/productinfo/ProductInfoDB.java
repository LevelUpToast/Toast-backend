package com.levelUpToast.levelUpToast.domain.model.product.productinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductInfoDB {
    private String text;
    private List<Long> productImgUrl;
}
