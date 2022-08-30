package com.levelUpToast.levelUpToast.domain.dataForm.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ProductResponseForm {
    private int detailCode;
    private String message;
    private Map<String,Object> data;
}
