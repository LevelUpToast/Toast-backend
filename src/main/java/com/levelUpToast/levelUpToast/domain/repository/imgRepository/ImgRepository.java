package com.levelUpToast.levelUpToast.domain.repository.imgRepository;

import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;

public interface ImgRepository {

    ImgItem add(ImgItem imgItem);

    ImgItem findBySeq(Long manageSeq);

    ImgItem findByImgUUID(String imgName);

}
