package com.levelUpToast.levelUpToast.vendor.service.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.domain.ResponseVendorTable;
import com.levelUpToast.levelUpToast.vendor.domain.VendorTable;

public interface VendorService {

    VendorTable registerVendor(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) throws LevelUpToastEx;

    ResponseVendorTable infoVendor(Long memberSeq) throws LevelUpToastEx;

    Boolean isVendor(Long memberSeq) throws LevelUpToastEx;
}
