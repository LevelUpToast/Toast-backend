package com.levelUpToast.levelUpToast.domain.product.productinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductInfo {
    private String text;
    private List<String> imgUrl;
}
