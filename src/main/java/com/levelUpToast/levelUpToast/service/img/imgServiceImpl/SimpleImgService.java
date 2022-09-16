package com.levelUpToast.levelUpToast.service.img.imgServiceImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.model.img.ImgItem;
import com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf.ImgRepository;
import com.levelUpToast.levelUpToast.service.img.imgServiceInf.ImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

public class SimpleImgService implements ImgService {

    private final ImgRepository imgRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    @Override
    public String createStoreImgName(String originalImgName) {
        String ext = extractExt(originalImgName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    @Override
    public String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

    @Override
    public ImgItem saveImg(MultipartFile multipartFile) throws LevelUpToastEx, IOException {
        if (multipartFile.isEmpty()) {
            log.warn("[ImgService log] : 요청한 이미지가 존재하지 않습니다.");
            throw new LevelUpToastEx("요청한 이미지가 존재하지 않습니다.", 111);
        }

        // system 저장
        String originalFilename = multipartFile.getOriginalFilename(); // 사용자가 보낸 IMG 이름
        String storeImgName = createStoreImgName(originalFilename); // 시스템 저장용 IMG 이름
        multipartFile.transferTo(new File(getFullPath(storeImgName))); // 파일 저장

        //DB 저장
        ImgItem imgItem = new ImgItem(originalFilename,storeImgName);
        imgRepository.save(imgItem);

        return imgItem;
    }


}
