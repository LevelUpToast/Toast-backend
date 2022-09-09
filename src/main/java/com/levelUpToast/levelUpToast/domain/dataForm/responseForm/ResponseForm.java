package com.levelUpToast.levelUpToast.domain.dataForm.responseForm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ResponseForm<V> {
    private int detailCode;
    private String message;
    private Map<String, V> data;
}
