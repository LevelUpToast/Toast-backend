package com.levelUpToast.levelUpToast.product.domain.data.productinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseProductInfo {
    String text;
    private List<String> productImgUrl;
}
