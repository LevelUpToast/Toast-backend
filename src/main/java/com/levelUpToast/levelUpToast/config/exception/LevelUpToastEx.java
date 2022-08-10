package com.levelUpToast.levelUpToast.config.exception;

public class LevelUpToastEx extends Exception{
    private final int ERR_CODE;

    public LevelUpToastEx(String message, int err_code) {
        super(message);
        ERR_CODE = err_code;
    }

    public int getERR_CODE() {
        return ERR_CODE;
    }
}
