package com.levelUpToast.levelUpToast.rest.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.dataForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.product.SimpleProductService;
import com.levelUpToast.levelUpToast.service.vendor.vendorServiceInf.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final SimpleProductService simpleProductService;
    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;
    private final VendorService vendorService;

    /**
     * 제품등록, 제품 정보 업데이트를 요청한 멤버의 무결성을 확인한다.
     * 사용자의 접근 권한과 정보가 일치하는지 확인하고, 발급된 토큰 정보를 비교한다.
     * @param requestSeq 클라이언트로부터 요청된 SEQ 받아 데이터를 확인한다.
     *                   작업 과정에서 토큰, 권한 정보를 확인한다.
     * @return 멤버 정보를 추출하고 리턴한다.
     */
    private Member checkMember(String requestSeq){
        try {
            Optional<Member> findMem = memberRepository.findByManageSeq(tokenManager.findMemberSeqByToken(requestSeq));
            if (findMem.isEmpty()) {
                log.warn("[ProductService log] : 토큰은 존재하지만, manageSeq 연결된 회원 없음");
                throw new LevelUpToastEx("토큰은 존재하지만, manageSeq 연결된 회원 없음", 131);
            }

            Member member = findMem.get();
            if (!vendorService.isVendor(member.getManageSeq())) {
                log.warn("[ProductService log] : 판매자 권한이 존재하지 않습니다.\n 이름 = {}", member.getName());
                throw new LevelUpToastEx(member.getName() + " 님은 판매자 권한이 존재하지 않습니다.", 132);
            }
            return member;
        } catch (LevelUpToastEx e) {
            new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
        return null;
    }

    /**
     * 요청한 클라이언트가 일치한지 확인하는 메소드
     * 인증절차에서 문제가 있다면 throw  발생하여 문제를 알려준다.
     * @param originalProductVendor 제품을 등록한 클라이언트 SEQ 정보를 받는다.
     * @param requestSeq 수정을 요청한 클라이언트의 SEQ
     */
    private void checkProductSEQ(Long originalProductVendor, Long requestSeq){
        try {
            if(originalProductVendor.equals(requestSeq)) { //삭제 요청한 Seq와 판매자의 seq가 같은지 확인한다.
                log.warn("[ProductService log] 판매자 정보가 일치하지 않습니다. 요청된 판매자 seq = {}\t제품 판매자 seq = {}", requestSeq, originalProductVendor);
                throw new LevelUpToastEx("판매자 정보가 일치하지 않습니다.", 54);
            }
        } catch (LevelUpToastEx e) {
            new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }


    /**
     * 클라이언트로 부터 제품을 등록하기위한 컨트롤러
     * @param form 사용자로부터 제품을 등록할 내용를 받는다.
     * @return 클라이언트에서 처리된 결과 값을 반환한다.
     */
    @PostMapping("/product/")
    public ResponseForm<Object> registerProduct(@RequestBody ProductRequestForm form) {
        try {
            Member member = checkMember(form.getVendorToken());
            if (member == null)
                throw new LevelUpToastEx("DataBase Exception", 52);

            HashMap<String, Object> data = new HashMap<>();
            data.put("product", simpleProductService.registerProduct(form, member.getManageSeq()));
            log.info("[ProductService log] : 상품 등록이 완료 되었습니다.");

            return new ResponseForm<>(-1, "상품 등록이 완료 되었습니다.", data);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    @GetMapping("/product/{productSeq}")
    public ResponseForm<Object> getProduct(@PathVariable("productSeq") Long productSeq){
        Map<String, Object> data = new LinkedHashMap<>();
        Optional<Product> product = simpleProductService.getProduct(productSeq);
        if (product.isEmpty()){
            log.info("[ProductService log] 요청된 제품 Seq 없거나 잘못된 번호입니다. SEQ = {}", productSeq);
            return new ResponseForm<>(53, "요청된 제품 Seq 없거나 잘못된 번호입니다." + productSeq, null);
        }
        data.put("Product", product);
        log.info("[ProductService log] 제품 정보 요청이 되었습니다. SEQ = {}", productSeq);
        return new ResponseForm<>(-1, "상품상세  productSeq : " + productSeq, data);
    }



    @PostMapping("/product/{productSeq}")
    public ResponseForm<Object> updateProduct(@PathVariable("productSeq") Long changeProductSeq, @RequestBody ProductRequestForm form){
        try {
            Member member = checkMember(form.getVendorToken());
            Optional<Product> originalProduct = simpleProductService.getProduct(changeProductSeq);

            if (member == null)
                throw new LevelUpToastEx("DataBase Exception", 52);
            checkProductSEQ(originalProduct.orElseThrow().getVendorSeq(), member.getManageSeq());

            simpleProductService.updateProduct(originalProduct, changeProductSeq, form);
            log.info("[ProductService log] 제품 업데이트 완료 SEQ = {}", changeProductSeq);
            return new ResponseForm<>(-1, "상품업데이트 완료 : productSeq : " + changeProductSeq, null);
        } catch (LevelUpToastEx e) {
            return new ResponseForm<>(e.getERR_CODE(), e.getMessage(), null);
        }
    }

    @DeleteMapping("/product/{requestSeq}/{productSeq}")
    public ResponseForm<Object> deleteProduct(@PathVariable("requestSeq") Long requestSeq , @PathVariable("productSeq") Long productSeq){
        checkProductSEQ(simpleProductService.getProduct(productSeq).orElseThrow().getVendorSeq(), requestSeq);

        simpleProductService.deleteProduct(productSeq);
        log.info("[ProductService log] : 제품 삭제 완료!\t삭제요청한 판매자 SEQ = {},\t 삭제요청된 제품Seq = {}", requestSeq, productSeq);
        return new ResponseForm<>(-1, "상품 삭제를 완료했습니다.", null);

    }

}
