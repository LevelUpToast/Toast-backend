package com.levelUpToast.levelUpToast.rest.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.dataForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.dataForm.product.ProductResponseForm;
import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.product.Product;
import com.levelUpToast.levelUpToast.domain.product.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.product.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.product.productinfo.ProductInfo;
import com.levelUpToast.levelUpToast.domain.product.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.service.vendor.vendorServiceInf.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final MemberRepository memberRepository;
    private final VendorService vendorService;
    private final ProductRepository productRepository;
    private final TokenManager tokenManager;

    // 상품 등록
    @PostMapping("/product/register")
    public ProductResponseForm register(@RequestBody ProductRequestForm form) {
        try{
            Optional<Member> findMem = memberRepository.findByManageSeq(tokenManager.findMemberSeqByToken(form.getVendorToken()));
            if(findMem.isEmpty()){
                return new ProductResponseForm(999999, " 500 서버 에러 토큰은 존재하지만 manageSeq와 연결된 회원 없음", null);
            }
            Member member = findMem.get();

            if(!vendorService.isVendor(member.getManageSeq())){
                return new ProductResponseForm(999999, member.getName() +" 님은 판매자 권한이 존재하지 않습니다.", null);
            }


            Product product = new Product(
                    form.getTitle(),
                    form.getInitialImgUrl(),
                    form.getTag(),
                    new FundingInfo(0, form.getFinalAmount(), form.getDeadline()), // 내부 데이터를 통해 처리
                    0, // 좋아요 초기화
                    member.getManageSeq(), // 내부 데이터를 통해 처리
                    new ProductInfo(form.getText(), form.getProductImgUrl()),
                    form.getBuyOption(), // -> 현재 문제점
                    new Review(0, 0, 0, 0, 0) // 새로운 상품 등록으로 0 초기화
            );
            productRepository.saveProduct(product);
            HashMap<String, Object> data = new HashMap<>();
            data.put("product", product);
            return new ProductResponseForm(-1,"상품 등록이 완료 되었습니다.",data);

        }catch (LevelUpToastEx e){
            return new ProductResponseForm(e.getERR_CODE(),e.getMessage(),null);
        }
    }


    //상품 취소


    // 상품 업데이트


}
