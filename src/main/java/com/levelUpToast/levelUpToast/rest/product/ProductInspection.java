package com.levelUpToast.levelUpToast.rest.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.product.SimpleProductService;
import com.levelUpToast.levelUpToast.service.vendor.vendorServiceInf.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ProductInspection {
    private final SimpleProductService simpleProductService;
    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;
    private final VendorService vendorService;

    /**
     * 제품등록, 제품 정보 업데이트를 요청한 멤버의 무결성을 확인한다.
     * 사용자의 접근 권한과 정보가 일치하는지 확인하고, 발급된 토큰 정보를 비교한다.
     * @param requestSeq 클라이언트로부터 요청된 SEQ 받아 데이터를 확인한다. 작업 과정에서 토큰, 권한 정보를 확인한다.
     * @return 멤버 정보를 추출하고 리턴한다.
     */
    public Member checkMember(String requestSeq) throws LevelUpToastEx {
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
    }

    /**
     * 요청한 클라이언트가 일치한지 확인하는 메소드
     * 인증절차에서 문제가 있다면 throw 발생하여 문제를 알려준다.
     * 문제가 없다면 그냥 실행이후 행동 없이 종류된다.
     * @param originalProductVendor 등록된 SEQ 정보를 받는다.
     * @param requestSeq 수정을 요청한 클라이언트의 SEQ
     */
    public void checkProductSEQ(Long originalProductVendor, Long requestSeq) throws LevelUpToastEx {
        if (!Objects.equals(originalProductVendor, requestSeq)) { //삭제 요청한 Seq와 판매자의 seq가 같은지 확인한다.
            log.warn("[ProductService log] 판매자 정보가 일치하지 않습니다. 요청된 판매자 seq = {}\t제품 판매자 seq = {}", requestSeq, originalProductVendor);
            throw new LevelUpToastEx("판매자 정보가 일치하지 않습니다.", 54);
        }
    }

    /**
     * Product 무결섬을 체크하기 위한 메소드
     * productSeq 넣어 product를 불러오고 데이터가 유효한지 잘못된 데이터인지 판단하고 문제가 없다면 Return
     * @param productSeq 확인할 ProductSEQ 정보를 입력받는 파라미터
     * @return 입력받은 ProductSEQ 검사하고 문제가 없다면 Product 데이터를 Return
     * @throws LevelUpToastEx 오류가 발생한다면 오류 코드와 메세지를 반환
     */
    public Optional<Product> checkProduct(Long productSeq) throws LevelUpToastEx{
        Optional<Product> product = simpleProductService.getProduct(productSeq);
        if (product.isEmpty()) {
            log.info("[ProductService log] 요청된 제품 Seq 없거나 잘못된 번호입니다. SEQ = {}", productSeq);
            throw new LevelUpToastEx("요청된 제품 Seq 없거나 잘못된 번호입니다.", 53);
        }
        return product;
    }

}
