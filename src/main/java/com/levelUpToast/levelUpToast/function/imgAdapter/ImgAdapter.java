package com.levelUpToast.levelUpToast.function.imgAdapter;

import java.util.List;

public interface ImgAdapter {

    List<String> extractImgUUID(List<Long> imgSeq);

    List<Long> extractImgSeq(List<String> imgUUIDList);
}
