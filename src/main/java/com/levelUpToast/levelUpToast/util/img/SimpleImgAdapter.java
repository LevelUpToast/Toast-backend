package com.levelUpToast.levelUpToast.util.img;

import com.levelUpToast.levelUpToast.domain.UseCase.img.adapter.ImgAdapter;
import com.levelUpToast.levelUpToast.domain.repository.imgRepository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleImgAdapter implements ImgAdapter {
    private final ImgRepository imgRepository;

    /**
     * 서버에 저장된 이미지 파일을 UUID 값으로 변환하는 메소드
     *
     * @param imgSEQ 변환을 원하는 이미지 SEQ
     * @return SEQ 해당되는 UUID 값을 반환
     */
    @Override
    public List<String> extractImgUUID(List<Long> imgSEQ) {
        List<String> imgUUIDList = new ArrayList<>();
        for (Long SEQ : imgSEQ)
            imgUUIDList.add(imgRepository.findBySeq(SEQ).getStoreFileName());
        return imgUUIDList;
    }

    /**
     * 사용자로 부터 UUID 값을 받아 SEQ 변환하는 메소드
     *
     * @param imgUUID 변환을 원하는 UUID
     * @return UUID 값을 해당되는 SEQ 반환
     */
    @Override
    public List<Long> extractImgSeq(List<String> imgUUID) {
        ArrayList<Long> imgSeqList = new ArrayList<>();
        for (String UUID : imgUUID)
            imgSeqList.add(imgRepository.findByImgUUID(UUID).getManageSeq());
        return imgSeqList;
    }
}
