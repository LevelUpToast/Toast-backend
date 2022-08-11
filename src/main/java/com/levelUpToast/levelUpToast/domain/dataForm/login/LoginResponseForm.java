package com.levelUpToast.levelUpToast.domain.dataForm.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;

@Data
@AllArgsConstructor
public class LoginResponseForm {
    private int detailCode;
    private String message;
    private Map<String,String> data;
}
