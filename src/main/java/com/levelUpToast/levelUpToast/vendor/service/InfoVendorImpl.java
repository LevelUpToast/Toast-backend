package com.levelUpToast.levelUpToast.vendor.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.service.vendor.InfoVendor;
import com.levelUpToast.levelUpToast.vendor.domain.ResponseVendorTable;
import com.levelUpToast.levelUpToast.vendor.domain.VendorTable;
import com.levelUpToast.levelUpToast.vendor.repository.vendorRepsitoryInf.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoVendorImpl implements InfoVendor {

    private final VendorRepository vendorRepository;

    @Override
    public ResponseVendorTable infoVendor(Long memberSeq) throws LevelUpToastEx {
        Optional<VendorTable> vendor = vendorRepository.findVendorBySeq(memberSeq);
        return new ResponseVendorTable(vendor.orElseThrow().getVendorName(), vendor.orElseThrow().getVendorProfileImg(), vendor.orElseThrow().getVendorIntroduction());
    }
}
