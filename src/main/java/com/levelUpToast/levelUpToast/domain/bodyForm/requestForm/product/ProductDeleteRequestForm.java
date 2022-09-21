package com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product;

import lombok.Data;

@Data
public class ProductDeleteRequestForm {
    private String vendorToken; // 판매자 토큰
    private String productSEQ; // 판매자 토큰
}
