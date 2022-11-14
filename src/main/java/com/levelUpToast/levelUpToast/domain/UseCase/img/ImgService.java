package com.levelUpToast.levelUpToast.domain.UseCase.img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ImgService {


    String getFullPath(String fileName);// 파일이름으로 전체 경로 만들기

    ImgItem saveImg(MultipartFile multipartFile) throws LevelUpToastEx, IOException; // 단일 이미지 저장


}
