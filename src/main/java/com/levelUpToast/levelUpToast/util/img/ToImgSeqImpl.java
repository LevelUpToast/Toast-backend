package com.levelUpToast.levelUpToast.util.img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.Img.ToImgSeq;
import com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToImgSeqImpl implements ToImgSeq {
    private final ImgRepository imgRepository;

    private long findUUID(String imgUUID) throws LevelUpToastEx {
        return imgRepository.findByImgUUID(imgUUID).getManageSeq();
    }

    @Override
    public List<Long> extractImgSeq(List<String> imgUUIDList) throws LevelUpToastEx {
        ArrayList<Long> imgSeqList = new ArrayList<>();
        for (String UUID : imgUUIDList)
            imgSeqList.add(findUUID(UUID));
        return imgSeqList;
    }

    @Override
    public Long extractImgSeq(String UUID) throws LevelUpToastEx {
        return findUUID(UUID);
    }
}
