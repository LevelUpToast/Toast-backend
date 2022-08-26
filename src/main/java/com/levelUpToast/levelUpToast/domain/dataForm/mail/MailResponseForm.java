package com.levelUpToast.levelUpToast.domain.dataForm.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailResponseForm {
    private int detailCode;
    private String message;
    private String data;
}
