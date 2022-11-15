package com.levelUpToast.levelUpToast.vendor.domain;

import lombok.Data;

@Data
public class VendorTable {
    private Long vendorSeq;
    private String vendorName;
    private String vendorProfileImg;
    private String vendorIntroduction;

    public VendorTable(Long vendorSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) {
        this.vendorSeq = vendorSeq;
        this.vendorName = vendorName;
        this.vendorProfileImg = vendorProfileImg;
        this.vendorIntroduction = vendorIntroduction;
    }
}