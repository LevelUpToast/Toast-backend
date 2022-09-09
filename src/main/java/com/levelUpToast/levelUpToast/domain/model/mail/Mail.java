package com.levelUpToast.levelUpToast.domain.model.mail;

import lombok.Data;

@Data
public class Mail {
    public Mail(String toAddress) {
        ToAddress = toAddress;
    }

    private String ToAddress;

    private String title;

    private String text;

    private String Code;
}
