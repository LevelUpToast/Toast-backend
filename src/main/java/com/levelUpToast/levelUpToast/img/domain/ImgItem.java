package com.levelUpToast.levelUpToast.img.domain;

import lombok.Data;

@Data
public class ImgItem {
    // DB 저장용 객체
    private long manageSeq;
    private String uploadFileName; // 업로드 이미지 이름
    private String storeFileName; // 시스템 내부 저장 파일 이름

    public ImgItem(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
