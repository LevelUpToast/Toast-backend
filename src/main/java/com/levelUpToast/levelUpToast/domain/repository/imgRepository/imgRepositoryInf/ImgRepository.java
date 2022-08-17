package com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf;

import com.levelUpToast.levelUpToast.domain.img.ImgItem;

public interface ImgRepository {

    ImgItem save(ImgItem imgItem);

    ImgItem findBySeq(Long manageSeq);

    ImgItem findByImgName(String imgName);

}
