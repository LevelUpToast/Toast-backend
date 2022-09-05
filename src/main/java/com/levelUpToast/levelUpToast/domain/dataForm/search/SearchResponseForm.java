package com.levelUpToast.levelUpToast.domain.dataForm.search;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class SearchResponseForm {
    private int detailCode;
    private String message;
    private Map<String,Object> data;
}
