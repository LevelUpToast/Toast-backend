package com.levelUpToast.levelUpToast.product.controller;

import com.levelUpToast.levelUpToast.product.service.productServiceInf.ProductService;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.vendor.service.vendor.InfoVendor;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductDeleteRequestForm;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.member.domain.Member;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.check.UserIntegrityVerification;
import com.levelUpToast.levelUpToast.util.utilInf.ProblemCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService simpleProductService;

    @PostMapping("/product")
    public ResponseForm<String> registerProduct(@RequestBody ProductRequestForm form) throws SQLException {
        try {
            return new ResponseForm<>(-1, "상품 등록이 완료 되었습니다. 상품 등록된 SEQ 번호  : "  + simpleProductService.registerProduct(form), null);
        } catch (LevelUpToastEx e) {
            log.info("[ProductService log] : " + e.getMessage());
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    @GetMapping("/product/{productSeq}")
    public ResponseForm<Object> getProduct(@PathVariable("productSeq") Long productSeq) throws SQLException {
        try {
            return new ResponseForm<>(-1, "상품상세  productSeq : " + productSeq, simpleProductService.getProduct(productSeq));
        }catch (LevelUpToastEx e){
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    @PostMapping("/product/{productSeq}")
    public ResponseForm<Object> updateProduct(@PathVariable("productSeq") Long changeProductSeq, @RequestBody ProductRequestForm form) throws SQLException {
        try {
            simpleProductService.updateProduct(changeProductSeq, form);
            return new ResponseForm<>(-1, "상품업데이트 완료 : productSeq : " + changeProductSeq, null);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    @DeleteMapping("/product")
    public ResponseForm<Object> deleteProduct(@RequestBody ProductDeleteRequestForm form) throws SQLException {
        try {
            simpleProductService.deleteProduct(form);
            return new ResponseForm<>(-1, "상품 삭제를 완료했습니다.", null);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }


    }

}
