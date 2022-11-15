package com.levelUpToast.levelUpToast.img.service;

import com.levelUpToast.levelUpToast.bodyForm.requestForm.img.ImgRequestForm;
import com.levelUpToast.levelUpToast.img.service.imgServiceInf.FullPathImg;
import com.levelUpToast.levelUpToast.img.service.imgServiceInf.ImgService;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.img.service.imgServiceInf.ImgSaveRepository;
import com.levelUpToast.levelUpToast.img.domain.ImgItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor

public class SimpleImgService implements ImgService {
    private final ImgSaveRepository imgSaveRepository;
    private final FullPathImg fullPathImg;

    @Override
    public String getFullPath(String fileName) {
        return fullPathImg.getFullPath(fileName);
    }

    public ImgItem saveImg(MultipartFile multipartFile) throws LevelUpToastEx, IOException {
        return imgSaveRepository.saveImg(multipartFile);
    }

    @Override
    public Map<String, String>imgSaveService(ImgRequestForm imgRequestForm )throws LevelUpToastEx, IOException {
        Map<String, String> data = new HashMap<>();
        ImgItem imgItem = saveImg(imgRequestForm.getImgFile());
        data.put("imgURL", imgItem.getStoreFileName());
        log.info("[ImgController log] : 이미지 저장 성공 originalImgName = {}, uploadImgName = {}", imgItem.getUploadFileName(), imgItem.getStoreFileName());
        return data;
    }

}
