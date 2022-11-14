package com.levelUpToast.levelUpToast.util.img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.Img.ToImgUUID;
import com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToImgUUIDImpl implements ToImgUUID {
    private final ImgRepository imgRepository;

    @Override
    public List<String> extractImgUUID(List<Long> imgSEQ) throws LevelUpToastEx {
        List<String> imgUUIDList = new ArrayList<>();
        for (Long SEQ : imgSEQ)
            imgUUIDList.add(imgRepository.findBySeq(SEQ).getStoreFileName());
        return imgUUIDList;
    }

    @Override
    public String extractImgUUID(Long imgSEQ) throws LevelUpToastEx {
        return imgRepository.findBySeq(imgSEQ).getStoreFileName();
    }
}
