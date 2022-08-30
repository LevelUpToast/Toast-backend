package com.levelUpToast.levelUpToast.domain.dataForm.mainPage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class MainPageResponseForm {
    private int detailCode;
    private String message;
    private Map<String,Object> data;
}
