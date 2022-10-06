package com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryImpl;

import com.levelUpToast.levelUpToast.domain.UseCase.img.adapter.ImgAdapter;
import com.levelUpToast.levelUpToast.domain.data.vendor.VendorTable;
import com.levelUpToast.levelUpToast.domain.repository.vendorRepository.vendorRepsitoryInf.VendorRepository;
import com.levelUpToast.levelUpToast.domain.data.vendor.DataBaseVendorTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class MemoryVendorRepository implements VendorRepository {

    private static final Map<Long, DataBaseVendorTable> vendorStore = new ConcurrentHashMap<>();
    private final ImgAdapter simpleImgAdapter;

    private VendorTable changeImgToUUID(DataBaseVendorTable vendor) {
        return new VendorTable(
                vendor.getVendorSeq(),
                vendor.getVendorName(),
                simpleImgAdapter.extractImgUUID(vendor.getVendorProfileImg()),
                vendor.getVendorIntroduction());
    }

    private DataBaseVendorTable changeImgToSEQ(VendorTable vendor) {
        return new DataBaseVendorTable(
                vendor.getVendorSeq(),
                vendor.getVendorName(),
                simpleImgAdapter.extractImgSeq(vendor.getVendorProfileImg()),
                vendor.getVendorIntroduction());
    }

    @Override
    public void vendorSave(VendorTable vendorTable) {
        DataBaseVendorTable dataBaseVendorTable = changeImgToSEQ(vendorTable);
        dataBaseVendorTable.setVendorSeq(dataBaseVendorTable.getVendorSeq());
        vendorStore.put(dataBaseVendorTable.getVendorSeq(), dataBaseVendorTable);
    }

    @Override
    public Optional<VendorTable> findVendorBySeq(Long vendorSeq) {
        return findAllVendor().stream()
                .filter(m -> m.getVendorSeq().equals(vendorSeq))
                .findFirst();
    }

    @Override
    public ArrayList<VendorTable> findAllVendor() {
        ArrayList<VendorTable> chVendorTable = new ArrayList<>();
        for (DataBaseVendorTable responseVendorTable : vendorStore.values())
            chVendorTable.add(changeImgToUUID( responseVendorTable));
        return chVendorTable;
    }

    @Override
    public void vendorUpdate(Long productSeq, VendorTable newVendorTable) {
        if (vendorStore.containsKey(productSeq)) {
            DataBaseVendorTable dataBaseVendorTable = changeImgToSEQ(newVendorTable);
            dataBaseVendorTable.setVendorSeq(productSeq);
            vendorStore.put(productSeq, dataBaseVendorTable);
        }
    }

    @Override
    public void vendorRemove(Long vendorSeq) {
        if (!findVendorBySeq(vendorSeq).isEmpty()) {
            vendorStore.remove(vendorSeq);
        }
    }
}
