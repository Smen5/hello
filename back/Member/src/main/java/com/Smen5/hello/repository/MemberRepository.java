package com.Smen5.hello.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Smen5.hello.constant.RoleConstant;
import com.Smen5.hello.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{
	Optional<Member> findByUuid(String uuid);
	
	boolean existsByUuid(String uuid);
	
	@Query("SELECT m FROM Member m WHERE m.uuid IN :uuids")
	List<Member> findAllByUuid(@Param("uuids") List<String> uuids);
	
	List<Member> findByRole(RoleConstant role);
}
