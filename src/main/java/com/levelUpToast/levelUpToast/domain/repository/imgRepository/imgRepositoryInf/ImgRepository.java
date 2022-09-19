package com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf;

import com.levelUpToast.levelUpToast.domain.model.img.ImgItem;

public interface ImgRepository {

    ImgItem add(ImgItem imgItem);

    ImgItem findBySeq(Long manageSeq);

    ImgItem findByImgUUID(String imgName);

}
