package com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf;

import com.levelUpToast.levelUpToast.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원 저장 (Create)
    Member save(Member member);

    // manageSeq 값으로 회원 조회 (Read)
    Optional<Member> findByManageSeq(Long manageSeq);

    // 모든 회원 조회 (Read)
    List<Member> findAllMember();

    // 회원 정보 변경 (Update)
    Member update(Long memberSeq, Member updatedMember);


    // manageSeq 값으로 회원 삭제 (Delete)
    // 조건 ( manager 혹은 member 당사자만 삭제 가능)
    void delete(Long manageSeq);

}
