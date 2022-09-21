package com.levelUpToast.levelUpToast.domain.data.product.data.productinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseProductInfo {
    String text;
    private List<String> productImgUrl;
}
