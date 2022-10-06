package com.levelUpToast.levelUpToast.domain.data.vendor;

import lombok.Data;

@Data
public class DataBaseVendorTable {
    private Long vendorSeq;
    private String vendorName;
    private Long vendorProfileImg; // 향후 vendor 이미지 응답시 String UUID 변환 필요
    private String vendorIntroduction;

    public DataBaseVendorTable(Long vendorSeq, String vendorName, Long vendorProfileImg, String vendorIntroduction) {
        this.vendorSeq = vendorSeq;
        this.vendorName = vendorName;
        this.vendorProfileImg = vendorProfileImg;
        this.vendorIntroduction = vendorIntroduction;
    }
}
