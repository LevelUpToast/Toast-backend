package com.levelUpToast.levelUpToast.util.img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.Img.ImgAdapter;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.Img.ToImgSeq;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.Img.ToImgUUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimpleImgAdapter implements ImgAdapter {
    private final ToImgUUID toImgUUID;
    private final ToImgSeq toImgSeq;

    /**
     * 서버에 저장된 이미지 파일을 UUID 값으로 변환하는 메소드
     *
     * @param imgSEQ 변환을 원하는 이미지 SEQ
     * @return SEQ 해당되는 UUID 값을 반환
     */
    @Override
    public List<String> extractImgUUID(List<Long> imgSEQ) throws LevelUpToastEx {
        return toImgUUID.extractImgUUID(imgSEQ);
    }

    @Override
    public String extractImgUUID(Long imgSEQ) throws LevelUpToastEx {
        return toImgUUID.extractImgUUID(imgSEQ);
    }

    /**
     * 사용자로 부터 UUID 값을 받아 SEQ 변환하는 메소드
     *
     * @param imgUUID 변환을 원하는 UUID
     * @return UUID 값을 해당되는 SEQ LIST 반환
     */
    @Override
    public List<Long> extractImgSeq(List<String> imgUUID) throws LevelUpToastEx {
       return toImgSeq.extractImgSeq(imgUUID);
    }

    /**
     * 사용자로 부터 UUID 값을 받아 SEQ 변환하는 메소드
     *
     * @param imgUUID 변환을 원하는 UUID
     * @return UUID 값을 해당되는 SEQ  반환
     */
    @Override
    public Long extractImgSeq(String imgUUID) throws LevelUpToastEx {
        return toImgSeq.extractImgSeq(imgUUID);
    }
}
