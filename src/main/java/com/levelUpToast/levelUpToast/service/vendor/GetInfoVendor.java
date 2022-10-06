package com.levelUpToast.levelUpToast.service.vendor;

import com.levelUpToast.levelUpToast.domain.UseCase.vendor.InfoVendor;
import com.levelUpToast.levelUpToast.domain.data.vendor.ResponseVendorTable;
import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;
import com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetInfoVendor implements InfoVendor {

    private final VendorRepository vendorRepository;

    @Override
    public ResponseVendorTable infoVendor(Long memberSeq) {
        Optional<VendorTable> vendor = vendorRepository.findVendorBySeq(memberSeq);
        return new ResponseVendorTable(vendor.orElseThrow().getVendorName(), vendor.orElseThrow().getVendorProfileImg(), vendor.orElseThrow().getVendorIntroduction());
    }
}
