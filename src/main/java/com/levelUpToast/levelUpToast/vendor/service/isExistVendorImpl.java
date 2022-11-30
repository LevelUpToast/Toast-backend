package com.levelUpToast.levelUpToast.vendor.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.service.vendor.IsExistVendor;
import com.levelUpToast.levelUpToast.vendor.repository.vendorRepsitoryInf.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class isExistVendorImpl implements IsExistVendor {
    private final VendorRepository vendorRepository;
    @Override
    public Boolean isExistVendor(Long memberSeq) throws LevelUpToastEx {
        return vendorRepository.findVendorBySeq(memberSeq).isPresent();
    }
}
