package com.levelUpToast.levelUpToast.service.img;


import com.levelUpToast.levelUpToast.domain.UseCase.img.service.FullPathImg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class GetFullPathImg implements FullPathImg {
    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

}
