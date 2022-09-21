package com.levelUpToast.levelUpToast.service.vendor.vendorServiceInf;

import com.levelUpToast.levelUpToast.domain.data.vendor.Vendor;

public interface VendorService {

    Vendor register(Long memberSeq, String vendorName, Long vendorProfileImg, String vendorIntroduction);

    Boolean isVendor(Long memberSeq);
}
