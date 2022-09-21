package com.levelUpToast.levelUpToast.function.member.memberCheck;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.service.vendor.vendorServiceInf.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimpleMemberCheck implements MemberCheck{

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
     * 사용자의 중복되는 아이디가 있는지 확인하는 메서드
     * @param id 중복확인할 ID 입력 받는 파라미터
     * @return Boolean 형식으로 중복인지 알려준다.
     */
    @Override
    public Boolean idPresentCheck(String id) {
        return memberRepository.findByLoginId(id).isPresent();
    }
}
