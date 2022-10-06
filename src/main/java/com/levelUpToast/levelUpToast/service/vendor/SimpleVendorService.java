package com.levelUpToast.levelUpToast.service.vendor;

import com.levelUpToast.levelUpToast.domain.UseCase.vendor.InfoVendor;
import com.levelUpToast.levelUpToast.domain.UseCase.vendor.IsExistVendor;
import com.levelUpToast.levelUpToast.domain.UseCase.vendor.RegisterVendor;
import com.levelUpToast.levelUpToast.domain.UseCase.vendor.VendorService;
import com.levelUpToast.levelUpToast.domain.data.vendor.ResponseVendorTable;
import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SimpleVendorService implements VendorService {

    private final RegisterVendor registerVendor;
    private final InfoVendor getInfoVendor;
    private final IsExistVendor isExistVendor;


    @Override
    public VendorTable registerVendor(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) {
        return registerVendor.register(memberSeq, vendorName, vendorProfileImg, vendorIntroduction);
    }

    public ResponseVendorTable infoVendor(Long memberSeq) { return getInfoVendor.infoVendor(memberSeq); }

    @Override
    public Boolean isVendor(Long memberSeq) {
        return isExistVendor.isExistVendor(memberSeq);
    }
}
