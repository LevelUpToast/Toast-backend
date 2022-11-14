package com.levelUpToast.levelUpToast.service.member.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.config.token.tokenManager.tokenManagerInf.TokenManager;
import com.levelUpToast.levelUpToast.domain.UseCase.member.check.MemberIsCheck;
import com.levelUpToast.levelUpToast.domain.UseCase.vendor.VendorService;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberIsCheckImpl implements MemberIsCheck {
    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;
    private final VendorService vendorService;

    @Override
    public Member hasMember(String requestSeq) throws LevelUpToastEx {
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
}
