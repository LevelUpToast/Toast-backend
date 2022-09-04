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
import com.levelUpToast.levelUpToast.service.product.SimpleProductService;
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
    private final SimpleProductService simpleProductService;
    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;
    private final VendorService vendorService;
    // 상품 등록
    @PostMapping("/product/register")
    public ProductResponseForm register(@RequestBody ProductRequestForm form) {
        try {
            Optional<Member> findMem = memberRepository.findByManageSeq(tokenManager.findMemberSeqByToken(form.getVendorToken()));
            if (findMem.isEmpty()) {
                log.warn("[ProductService log] : 토큰은 존재하지만, manageSeq 연결된 회원 없음");
                throw new LevelUpToastEx("토큰은 존재하지만, manageSeq 연결된 회원 없음", 131);
            }

            Member member = findMem.get();
            if (!vendorService.isVendor(member.getManageSeq())) {
                log.warn("[ProductService log] : 판매자 권한이 존재하지 않습니다.\n 이름 = {}", member.getName());
                throw new LevelUpToastEx(member.getName() + " 님은 판매자 권한이 존재하지 않습니다.", 132);
            }

            HashMap<String, Object> data = new HashMap<>();
            data.put("product", simpleProductService.productRegister(form, member.getManageSeq()));

            log.info("[ProductService log] : 상품 등록이 완료 되었습니다.");
            return new ProductResponseForm(-1, "상품 등록이 완료 되었습니다.", data);

        } catch (LevelUpToastEx e) {
            return new ProductResponseForm(e.getERR_CODE(), e.getMessage(), null);
        }
    }

}