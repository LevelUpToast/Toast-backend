package com.levelUpToast.levelUpToast.auth.repository.memberRepositoryImpl;

import com.levelUpToast.levelUpToast.auth.domain.Member;
import com.levelUpToast.levelUpToast.auth.repository.memberRepositoryInf.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();
    private static final Map<String, Long> idList = new HashMap<>();
    private Long manageSeq = 0L;
    @Override
    public Member save(Member member) {
        member.setManageSeq(manageSeq++);
        store.put(member.getManageSeq(), member);
        idList.put(member.getId(), member.getManageSeq());
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
    public Optional<Member> findByLoginId(String loginId) {
        if(idList.containsKey(loginId)){
            return findByManageSeq(idList.get(loginId));
        }
        return Optional.empty();
    }

    @Override
    public Member update(Long memberSeq, Member updatedMember) {
        // update 향후 testing 필요
        Optional<Member> findMem = findByManageSeq(memberSeq);
        if(findMem.isEmpty()){
            return null;
        }
//        Member member = findMem.get();
//        member = updatedMember;
        return updatedMember;
    }

    @Override
    public void remove(Long manageSeq) {
        Optional<Member> findMem = findByManageSeq(manageSeq);
        if(findMem.isPresent()){
            store.remove(manageSeq);
        }
    }
}
