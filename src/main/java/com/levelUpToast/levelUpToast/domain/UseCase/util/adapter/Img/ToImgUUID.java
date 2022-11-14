package com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.Img;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


@Qualifier("ImgToUUIDImpl")
public interface ToImgUUID {
    List<String> extractImgUUID(List<Long> imgSeq) throws LevelUpToastEx;

    String extractImgUUID(Long imgSEQ) throws LevelUpToastEx;
}
