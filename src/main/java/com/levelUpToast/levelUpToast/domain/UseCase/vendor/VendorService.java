package com.levelUpToast.levelUpToast.domain.UseCase.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.vendor.ResponseVendorTable;
import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;

public interface VendorService {

    VendorTable registerVendor(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) throws LevelUpToastEx;

    ResponseVendorTable infoVendor(Long memberSeq) throws LevelUpToastEx;

    Boolean isVendor(Long memberSeq) throws LevelUpToastEx;
}
