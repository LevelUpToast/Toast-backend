package com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryImpl;

import com.levelUpToast.levelUpToast.domain.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new ConcurrentHashMap<>();

    @Override
    public Member save(Member member) {
        store.put(member.getManageSeq(), member);
        return member;
    }

    @Override
    public Optional<Member> findByManageSeq(Long manageSeq) {
        return findAllMember().stream()
                .filter(m -> m.getManageSeq().equals(manageSeq))
                .findFirst();
    }

    @Override
    public List<Member> findAllMember() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member update(Long memberSeq, Member updatedMember) {
        // update 향후 testing 필요
        Optional<Member> findMem = findByManageSeq(memberSeq);
        if(findMem.isPresent()){
            return null;
        }
        Member member = findMem.get();
        member = updatedMember;
        return member;
    }

    @Override
    public void delete(Long manageSeq) {
        Optional<Member> findMem = findByManageSeq(manageSeq);
        if(!findMem.isEmpty()){
            store.remove(manageSeq);
        }
    }
}
