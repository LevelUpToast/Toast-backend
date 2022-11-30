package com.levelUpToast.levelUpToast.vendor.service.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.domain.VendorTable;

public interface RegisterVendor {
    VendorTable register(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) throws LevelUpToastEx;
}
