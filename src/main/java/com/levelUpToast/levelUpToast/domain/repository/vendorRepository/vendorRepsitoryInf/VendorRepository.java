package com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf;

import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;

import java.util.ArrayList;
import java.util.Optional;

public interface VendorRepository {

    // 공급업체 저장
    void vendorSave(VendorTable vendorTable);

    // Seq로 공급업체 찾기
    Optional<VendorTable> findVendorBySeq(Long vendorSeq);

    // 모든 공급업체 반환
    ArrayList<VendorTable> findAllVendor();

    // 공급업체 정보 수정
    void vendorUpdate(Long productSeq, VendorTable newVendorTable);

    // 공급업체 제거
    void vendorRemove(Long vendorSeq);


}
