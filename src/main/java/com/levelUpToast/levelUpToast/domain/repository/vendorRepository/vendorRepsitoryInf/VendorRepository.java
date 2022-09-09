package com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf;

import com.levelUpToast.levelUpToast.domain.model.vendor.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorRepository {

    // 공급업체 저장
    Vendor vendorSave(Vendor vendor);

    // Seq로 공급업체 찾기
    Optional<Vendor> findVendorBySeq(Long vendorSeq);

    // 모든 공급업체 반환
    List<Vendor> findAllVendor();

    // 공급업체 정보 수정
    Vendor vendorUpdate(Long vendorSeq,Vendor newVendor);

    // 공급업체 제거
    void vendorRemove(Long vendorSeq);


}
