package com.levelUpToast.levelUpToast.controller;

import com.levelUpToast.levelUpToast.domain.UseCase.product.ProductService;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductDeleteRequestForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.UseCase.member.MemberCheck;
import com.levelUpToast.levelUpToast.domain.UseCase.product.ProblemCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService simpleProductService;
    private final ProblemCheck simpleProductInspection;
    private final MemberCheck simpleMemberCheck;

    /**
     * 클라이언트로 부터 제품을 등록하기위한 컨트롤러
     * @param form 사용자로부터 제품을 등록할 내용를 받는다.
     * @return 클라이언트에서 처리된 결과 값을 반환한다.
     */
    @PostMapping("/product")
    public ResponseForm<String> registerProduct(@RequestBody ProductRequestForm form) {
        try {
            Member member = simpleMemberCheck.isMember(form.getVendorToken());
            log.info(String.valueOf(form));
            String productSEQ =  simpleProductService.registerProduct(form, member.getManageSeq());
            if (productSEQ == null)
                throw new LevelUpToastEx("제품등록에 오류가 발생했습니다.", 142);
            log.info("[ProductService log] : 상품 등록이 완료 되었습니다.");
            return new ResponseForm<>(-1, "상품 등록이 완료 되었습니다. 상품 등록된 SEQ 번호  : "  + productSEQ, null);
        } catch (LevelUpToastEx e) {
            log.info("[ProductService log] : " + e.getMessage());
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    /**
     * Repository 저장된 데이터 정보를 불러와 호출한 클라이언트에게 다시 보내주는 컨트롤러
     * @param productSeq 클라이언트로부터 요청 받은 SEQ 번호
     * @return 요청한 데이터 처리 결과값을 반환해준다.
     */
    @GetMapping("/product/{productSeq}")
    public ResponseForm<Object> getProduct(@PathVariable("productSeq") Long productSeq) {
        try {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("Product", simpleProductInspection.isProduct(productSeq));
            data.put("recommendedProducts", simpleProductService.recommendationProduct());
            log.info("[ProductService log] 제품 정보 요청이 되었습니다. SEQ = {}", productSeq);
            return new ResponseForm<>(-1, "상품상세  productSeq : " + productSeq, data);
        }catch (LevelUpToastEx e){
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    /**
     * 이미 등록된 제품을 수정(Update)진핼할 때 요청되는 컨트롤러
     * @param changeProductSeq 수정을 원하는 ProductSEQ 입력을 받는다.
     * @param form 수정할 데이터를 ProductRequestForm 형식으로 받는다.
     * @return Product 수정을 완료한다면 처리 결과값을 반환해준다.
     */
    @PostMapping("/product/{productSeq}")
    public ResponseForm<Object> updateProduct(@PathVariable("productSeq") Long changeProductSeq, @RequestBody ProductRequestForm form) {
        try {
            Member member = simpleMemberCheck.isMember(form.getVendorToken());
            Optional<ResponseProductTable> originalProduct = simpleProductInspection.isProduct(changeProductSeq);

            simpleProductInspection.isProductSEQ(originalProduct.orElseThrow().getVendorSeq(), member.getManageSeq());
            simpleProductService.updateProduct(originalProduct, changeProductSeq, form);

            log.info("[ProductService log] 제품 업데이트 완료 SEQ = {}", changeProductSeq);
            return new ResponseForm<>(-1, "상품업데이트 완료 : productSeq : " + changeProductSeq, null);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    /**
     * 이미 등록된 Product 삭제하는 메소드이다.
     * @param form 클라이언트부터 ProductDeleteRequestForm 형식의 Form 받는다.
     * @return Product 삭제 처리 결과값을 반환해준다.
     */
    @DeleteMapping("/product")
    public ResponseForm<Object> deleteProduct(@RequestBody ProductDeleteRequestForm form) {
        try {
            Member member = simpleMemberCheck.isMember(form.getVendorToken());
            Long productSeq = Long.parseLong(form.getProductSEQ());

            simpleProductInspection.isProductSEQ(simpleProductInspection.isProduct(productSeq).orElseThrow().getVendorSeq(), member.getManageSeq());
            log.info("[ProductService log] : 제품 삭제 완료!\t삭제요청한 판매자 SEQ = {},\t 삭제요청된 제품Seq = {}", member.getManageSeq(), productSeq);
            simpleProductService.deleteProduct(productSeq);
            return new ResponseForm<>(-1, "상품 삭제를 완료했습니다.", null);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }


    }

}
