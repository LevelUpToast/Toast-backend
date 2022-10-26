package com.levelUpToast.levelUpToast.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.util.product.ProductIsProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class IsProduct implements ProductIsProduct {
    @Override
    public void isProductSEQ(Long originalProductVendor, Long requestSeq) throws LevelUpToastEx {
        if (!Objects.equals(originalProductVendor, requestSeq)) { //삭제 요청한 Seq 판매자의 seq 같은지 확인한다.
            log.warn("[ProductService log] 판매자 정보가 일치하지 않습니다. 요청된 판매자 seq = {}\t제품 판매자 seq = {}", requestSeq, originalProductVendor);
            throw new LevelUpToastEx("판매자 정보가 일치하지 않습니다.", 54);
        }
    }
}
