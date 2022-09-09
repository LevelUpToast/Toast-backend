package com.levelUpToast.levelUpToast.service.img.imgServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.model.img.ImgItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImgService {


    String getFullPath(String fileName); // 파일이름으로 전체 경로 만들기

    String createStoreImgName(String originalImgName); // 기존 이미지 이름으로 시스템 내부 저장용 이름 생성

    String extractExt(String originalFileName); // 기존 이미지 이름에서 확장자 추출

    ImgItem saveImg(MultipartFile multipartFile) throws LevelUpToastEx, IOException; // 단일 이미지 저장


}
