package com.levelUpToast.levelUpToast.domain.data.vendor;

import lombok.Data;

@Data
public class  ResponseVendorTable{
    private String vendorName;
    private String vendorProfileImg;
    private String vendorIntroduction;

    public ResponseVendorTable(String vendorName, String vendorProfileImg, String vendorIntroduction) {
        this.vendorName = vendorName;
        this.vendorProfileImg = vendorProfileImg;
        this.vendorIntroduction = vendorIntroduction;
    }
}