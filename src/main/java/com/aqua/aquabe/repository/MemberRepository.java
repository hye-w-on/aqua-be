package com.aqua.aquabe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aqua.aquabe.model.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findTopMemberBySocialId(String socialId);

    Member findTopMemberByEmail(String email);

}
