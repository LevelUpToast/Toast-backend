package com.levelUpToast.levelUpToast.domain.UseCase.img.adapter;

import java.util.List;

public interface ImgAdapter {

    List<String> extractImgUUID(List<Long> imgSeq);

    String extractImgUUID(Long imgSEQ);

    List<Long> extractImgSeq(List<String> imgUUIDList);
    Long extractImgSeq(String imgUUID);
}
