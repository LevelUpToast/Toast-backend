package com.levelUpToast.levelUpToast.domain.dataForm.img;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ImgSaveResponseForm {
    private int detailCode;
    private String message;
    private Map<String, String> data;
}
