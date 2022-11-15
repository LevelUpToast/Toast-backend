package com.levelUpToast.levelUpToast.img.service.imgServiceInf;

import com.levelUpToast.levelUpToast.bodyForm.requestForm.img.ImgRequestForm;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import java.io.IOException;
import java.util.Map;

public interface ImgService {
    String getFullPath(String fileName);// 파일이름으로 전체 경로 만들기

    Map<String, String> imgSaveService(ImgRequestForm imgRequestForm )throws LevelUpToastEx, IOException;

}
