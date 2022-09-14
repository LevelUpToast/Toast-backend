package com.levelUpToast.levelUpToast.function.productInspection;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.Product;

import java.util.Optional;

public interface ProblemCheck {
    Member checkMember(String requestSeq) throws LevelUpToastEx;

    void checkProductSEQ(Long originalProductVendor, Long requestSeq) throws LevelUpToastEx;

    Optional<Product> checkProduct(Long productSeq) throws LevelUpToastEx;
}
