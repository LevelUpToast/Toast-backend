package com.levelUpToast.levelUpToast.domain.vendor;

import lombok.Data;

@Data

public class Vendor {
    private Long vendorSeq;
    private String vendorName;
    private String vendorProfileImg;
    private String vendorIntroduction;

    public Vendor(String vendorName, String vendorProfileImg, String vendorIntroduction) {
        this.vendorName = vendorName;
        this.vendorProfileImg = vendorProfileImg;
        this.vendorIntroduction = vendorIntroduction;
    }
}
