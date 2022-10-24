package com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryImpl;

import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;
import com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf.ImgRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class MemoryImgRepository implements ImgRepository {
    private final Map<Long, ImgItem> store = new ConcurrentHashMap<>();
    private long manageSeq = 0L;

    @Override
    public ImgItem add(ImgItem imgItem) {
        imgItem.setManageSeq(++manageSeq);
        store.put(imgItem.getManageSeq(), imgItem);
        return imgItem;
    }

    @Override
    public ImgItem findBySeq(Long manageSeq) {
        return store.get(manageSeq);
    }

    @Override
    public ImgItem findByImgUUID(String imgName) {
        Optional<ImgItem> img = store.values().stream().filter(i -> i.getStoreFileName().equals(imgName)).findFirst();
        if (img.isEmpty())
            return null;
        return img.get();
    }


}
