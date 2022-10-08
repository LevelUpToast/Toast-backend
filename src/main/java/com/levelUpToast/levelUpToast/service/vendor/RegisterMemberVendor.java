package com.levelUpToast.levelUpToast.service.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.vendor.RegisterVendor;
import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;
import com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMemberVendor implements RegisterVendor {
    private final VendorRepository vendorRepository;

    @Override
    public VendorTable register(Long memberSeq, String vendorName, String vendorProfileImg, String vendorIntroduction) throws LevelUpToastEx {
        VendorTable dataBaseVendor = new VendorTable(memberSeq, vendorName, vendorProfileImg, vendorIntroduction);
        vendorRepository.vendorSave(dataBaseVendor);
        return dataBaseVendor;
    }
}
