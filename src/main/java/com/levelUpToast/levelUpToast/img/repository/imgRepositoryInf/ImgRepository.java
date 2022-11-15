package com.levelUpToast.levelUpToast.img.repository.imgRepositoryInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.img.domain.ImgItem;

public interface ImgRepository {

    ImgItem add(ImgItem imgItem) throws LevelUpToastEx;

    ImgItem findBySeq(Long manageSeq) throws LevelUpToastEx;

    ImgItem findByImgUUID(String imgName) throws LevelUpToastEx;

}
