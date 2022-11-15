package com.levelUpToast.levelUpToast.vendor.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.service.vendor.RegisterVendor;
import com.levelUpToast.levelUpToast.vendor.domain.VendorTable;
import com.levelUpToast.levelUpToast.vendor.repository.vendorRepsitoryInf.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterVendorImpl implements RegisterVendor {
    private final VendorRepository vendorRepository;

    @Override
    public VendorTable register(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) throws LevelUpToastEx {
        VendorTable dataBaseVendor = new VendorTable(memberSeq, vendorName, vendorProfileImg, vendorIntroduction);
        vendorRepository.vendorSave(dataBaseVendor);
        return dataBaseVendor;
    }
}
