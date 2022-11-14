package com.levelUpToast.levelUpToast.service.img;

import com.levelUpToast.levelUpToast.domain.UseCase.img.FullPathImg;
import com.levelUpToast.levelUpToast.domain.UseCase.img.ImgService;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.img.ImgSaveRepository;
import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Override
    public ImgItem saveImg(MultipartFile multipartFile) throws LevelUpToastEx, IOException {
        return imgSaveRepository.saveImg(multipartFile);
    }

}
