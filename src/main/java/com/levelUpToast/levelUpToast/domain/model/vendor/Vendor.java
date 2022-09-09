package com.levelUpToast.levelUpToast.domain.model.vendor;

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
