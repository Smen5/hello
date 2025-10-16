package com.Smen5.hello.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Smen5.hello.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{
	Optional<Member> findByUuid(String uuid);
	boolean existsByUuid(String uuid);
}
