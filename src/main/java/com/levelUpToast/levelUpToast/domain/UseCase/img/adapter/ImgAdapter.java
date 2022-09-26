package com.levelUpToast.levelUpToast.domain.UseCase.img.adapter;

import java.util.List;

public interface ImgAdapter {

    List<String> extractImgUUID(List<Long> imgSeq);

    List<Long> extractImgSeq(List<String> imgUUIDList);
}
