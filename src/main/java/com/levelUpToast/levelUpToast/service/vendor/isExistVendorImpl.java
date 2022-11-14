package com.levelUpToast.levelUpToast.service.vendor;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.vendor.IsExistVendor;
import com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf.VendorRepository;
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
