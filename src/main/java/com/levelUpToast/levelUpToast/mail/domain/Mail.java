package com.levelUpToast.levelUpToast.mail.domain;

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
