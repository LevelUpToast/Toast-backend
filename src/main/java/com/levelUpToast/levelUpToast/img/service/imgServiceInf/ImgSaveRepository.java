package com.levelUpToast.levelUpToast.img.service.imgServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.img.domain.ImgItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImgSaveRepository {
     ImgItem saveImg(MultipartFile multipartFile) throws LevelUpToastEx, IOException ;
}
