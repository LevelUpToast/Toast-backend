package com.levelUpToast.levelUpToast.domain.UseCase.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.vendor.ResponseVendorTable;

public interface InfoVendor {
    ResponseVendorTable infoVendor(Long memberSeq) throws LevelUpToastEx;
}
