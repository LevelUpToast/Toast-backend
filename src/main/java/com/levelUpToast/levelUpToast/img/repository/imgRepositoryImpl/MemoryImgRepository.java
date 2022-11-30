package com.levelUpToast.levelUpToast.img.repository.imgRepositoryImpl;

import com.levelUpToast.levelUpToast.img.domain.ImgItem;
import com.levelUpToast.levelUpToast.img.repository.imgRepositoryInf.ImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
@RequiredArgsConstructor
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
