package com.levelUpToast.levelUpToast.domain.data.product.data.productinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DataBaseProductInfo {
    private String text;
    private List<Long> productImgUrl;
}
