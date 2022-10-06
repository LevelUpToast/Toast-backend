package com.levelUpToast.levelUpToast.domain.UseCase.vendor;

import com.levelUpToast.levelUpToast.domain.data.vendor.ResponseVendorTable;
import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;

public interface VendorService {

    VendorTable registerVendor(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction);

    ResponseVendorTable infoVendor(Long memberSeq);

    Boolean isVendor(Long memberSeq);
}
