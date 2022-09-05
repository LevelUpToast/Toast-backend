package com.levelUpToast.levelUpToast.domain.dataForm.signUp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class SignUpResponseForm {
    private int detailCode;
    private String message;
    private Map<String, String> data;
}
