package com.levelUpToast.levelUpToast.vendor.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.service.vendor.InfoVendor;
import com.levelUpToast.levelUpToast.vendor.service.vendor.IsExistVendor;
import com.levelUpToast.levelUpToast.vendor.service.vendor.RegisterVendor;
import com.levelUpToast.levelUpToast.vendor.service.vendor.VendorService;
import com.levelUpToast.levelUpToast.vendor.domain.ResponseVendorTable;
import com.levelUpToast.levelUpToast.vendor.domain.VendorTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SimpleVendorService implements VendorService {

    private final RegisterVendor registerVendor;
    private final InfoVendor getInfoVendor;
    private final IsExistVendor isExistVendor;


    @Override
    public VendorTable registerVendor(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) throws LevelUpToastEx {
        return registerVendor.register(memberSeq, vendorName, vendorProfileImg, vendorIntroduction);
    }

    public ResponseVendorTable infoVendor(Long memberSeq) throws LevelUpToastEx { return getInfoVendor.infoVendor(memberSeq); }

    @Override
    public Boolean isVendor(Long memberSeq) throws LevelUpToastEx {
        return isExistVendor.isExistVendor(memberSeq);
    }
}
