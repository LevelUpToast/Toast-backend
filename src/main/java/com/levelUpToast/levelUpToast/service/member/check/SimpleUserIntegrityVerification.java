package com.levelUpToast.levelUpToast.service.member.check;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.member.check.MemberIsID;
import com.levelUpToast.levelUpToast.domain.UseCase.member.check.UserIntegrityVerification;
import com.levelUpToast.levelUpToast.domain.UseCase.member.check.MemberIsCheck;
import com.levelUpToast.levelUpToast.domain.data.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimpleUserIntegrityVerification implements UserIntegrityVerification {

    private final MemberIsID memberIsID;
    private final MemberIsCheck memberIsCheck;


    /**
     * 제품등록, 제품 정보 업데이트를 요청한 멤버의 무결성을 확인한다.
     * 사용자의 접근 권한과 정보가 일치하는지 확인하고, 발급된 토큰 정보를 비교한다.
     * @param requestSeq 클라이언트로부터 요청된 SEQ 받아 데이터를 확인한다. 작업 과정에서 토큰, 권한 정보를 확인한다.
     * @return 멤버 정보를 추출하고 리턴한다.
     */
    public Member isMember(String requestSeq) throws LevelUpToastEx {
        return memberIsCheck.hasMember(requestSeq);
    }

    /**
     * 사용자의 중복되는 아이디가 있는지 확인하는 메서드
     * @param id 중복확인할 ID 입력 받는 파라미터
     * @return Boolean 형식으로 중복인지 알려준다.
     */
    @Override
    public Boolean isIDPresent(String id) throws LevelUpToastEx {
        return memberIsID.isMemberID(id);
    }
}
