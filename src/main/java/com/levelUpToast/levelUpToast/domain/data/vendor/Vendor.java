package com.levelUpToast.levelUpToast.domain.data.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vendor {
    private Long vendorSeq;
    private String vendorName;
    private Long vendorProfileImg; // 향후 vendor 이미지 응답시 String UUID 변환 필요
    private String vendorIntroduction;
}
