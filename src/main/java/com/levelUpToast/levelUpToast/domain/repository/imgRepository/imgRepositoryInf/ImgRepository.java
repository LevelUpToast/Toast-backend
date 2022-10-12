package com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;

public interface ImgRepository {

    ImgItem add(ImgItem imgItem) throws LevelUpToastEx;

    ImgItem findBySeq(Long manageSeq) throws LevelUpToastEx;

    ImgItem findByImgUUID(String imgName) throws LevelUpToastEx;

}
