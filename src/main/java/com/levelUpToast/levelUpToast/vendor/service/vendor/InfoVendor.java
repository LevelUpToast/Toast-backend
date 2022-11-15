package com.levelUpToast.levelUpToast.vendor.service.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.domain.ResponseVendorTable;

public interface InfoVendor {
    ResponseVendorTable infoVendor(Long memberSeq) throws LevelUpToastEx;
}
