package com.immutech.ExerLytix.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.immutech.ExerLytix.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
	public Member findByEmail(String email);
	boolean existsByEmail(String email);

}
