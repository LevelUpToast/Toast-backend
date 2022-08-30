package com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryImpl;

import com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf.VendorRepository;
import com.levelUpToast.levelUpToast.domain.vendor.Vendor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryVendorRepository implements VendorRepository {

    private static Map<Long, Vendor> vendorStore = new ConcurrentHashMap<>();
    private Long manageSeq = 0L;

    @Override
    public Vendor vendorSave(Vendor vendor) {
        vendor.setVendorSeq(manageSeq++);
        vendorStore.put(vendor.getVendorSeq(), vendor);
        return vendor;
    }

    @Override
    public Optional<Vendor> findVendorBySeq(Long vendorSeq) {
        return findAllVendor().stream()
                .filter(m -> m.getVendorSeq().equals(vendorSeq))
                .findFirst();
    }

    @Override
    public List<Vendor> findAllVendor() {
        return new ArrayList<>(vendorStore.values());
    }

    @Override
    public Vendor vendorUpdate(Long vendorSeq, Vendor newVendor) {
        Optional<Vendor> findVendor = findVendorBySeq(vendorSeq);
        if (findVendor.isEmpty()) {
            return null;
        }
        Vendor vendor = findVendor.get();
        vendor = newVendor;
        return vendor;
    }

    @Override
    public void vendorRemove(Long vendorSeq) {
        if(!findVendorBySeq(vendorSeq).isEmpty()){
            vendorStore.remove(vendorSeq);
        }
    }
}
