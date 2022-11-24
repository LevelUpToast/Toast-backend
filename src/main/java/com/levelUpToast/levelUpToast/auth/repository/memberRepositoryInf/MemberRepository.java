package com.levelUpToast.levelUpToast.auth.repository.memberRepositoryInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.auth.domain.Member;


import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원 저장 (Create)
    Member save(Member member) throws LevelUpToastEx;

    // manageSeq 값으로 회원 조회 (Read)
    Optional<Member> findByManageSeq(Long manageSeq) throws LevelUpToastEx;

    // 모든 회원 조회 (Read)
    List<Member> findAllMember() throws LevelUpToastEx;

    // 회원 ID로 회원 조회
    Optional<Member> findByLoginId(String loginId) throws LevelUpToastEx;

    // 회원 정보 변경 (Update)
    Member update(Long memberSeq, Member updatedMember) throws LevelUpToastEx;


    // manageSeq 값으로 회원 삭제 (Delete)
    // 조건 ( manager 혹은 member 당사자만 삭제 가능)
    void remove(Long manageSeq) throws LevelUpToastEx;

}
