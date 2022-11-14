package com.levelUpToast.levelUpToast.domain.UseCase.img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImgSaveRepository {
     ImgItem saveImg(MultipartFile multipartFile) throws LevelUpToastEx, IOException ;
}
