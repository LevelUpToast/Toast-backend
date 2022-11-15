package com.levelUpToast.levelUpToast.img.service;


import com.levelUpToast.levelUpToast.img.service.imgServiceInf.FullPathImg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FullPathImpl implements FullPathImg {
    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

}
