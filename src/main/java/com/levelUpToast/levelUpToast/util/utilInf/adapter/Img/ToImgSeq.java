package com.levelUpToast.levelUpToast.util.utilInf.adapter.Img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

import java.util.List;

public interface ToImgSeq {
    List<Long> extractImgSeq(List<String> imgUUIDList) throws LevelUpToastEx;

    Long extractImgSeq(String imgUUID) throws LevelUpToastEx;
}
