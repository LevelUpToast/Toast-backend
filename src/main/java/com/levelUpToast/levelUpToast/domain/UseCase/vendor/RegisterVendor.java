package com.levelUpToast.levelUpToast.domain.UseCase.vendor;

import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;

public interface RegisterVendor {
    VendorTable register(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction);
}
