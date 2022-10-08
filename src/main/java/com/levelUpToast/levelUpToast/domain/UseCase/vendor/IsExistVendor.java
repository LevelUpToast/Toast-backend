package com.levelUpToast.levelUpToast.domain.UseCase.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

public interface IsExistVendor {
    Boolean isExistVendor(Long memberSeq) throws LevelUpToastEx;
}
