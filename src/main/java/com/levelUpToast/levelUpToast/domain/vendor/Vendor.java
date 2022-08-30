package com.levelUpToast.levelUpToast.domain.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vendor {
    private Long vendorSeq;
    private String vendorName;
    private String vendorProfileImg;
    private String vendorIntroduction;
}
