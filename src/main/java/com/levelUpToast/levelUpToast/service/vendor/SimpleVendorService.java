package com.levelUpToast.levelUpToast.service.vendor;

import com.levelUpToast.levelUpToast.domain.UseCase.vendor.VendorService;
import com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf.VendorRepository;
import com.levelUpToast.levelUpToast.domain.data.vendor.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SimpleVendorService implements VendorService {
    private final VendorRepository vendorRepository;

    @Override
    public Vendor register(Long memberSeq,String vendorName, Long vendorProfileImg, String vendorIntroduction) {
        Vendor vendor = new Vendor(memberSeq, vendorName, vendorProfileImg, vendorIntroduction);
        vendorRepository.vendorSave(vendor);
        return vendor;
    }

    @Override
    public Boolean isVendor(Long memberSeq) {
        return vendorRepository.findVendorBySeq(memberSeq).isPresent();
    }
}