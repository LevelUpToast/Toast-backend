package com.levelUpToast.levelUpToast.service.vendor;

import com.levelUpToast.levelUpToast.domain.UseCase.vendor.IsExistVendor;
import com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExistCheckVendor implements IsExistVendor {
    private final VendorRepository vendorRepository;
    @Override
    public Boolean isExistVendor(Long memberSeq) {
        return vendorRepository.findVendorBySeq(memberSeq).isPresent();
    }
}
