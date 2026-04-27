package com.turkcell.library.repository;

import com.turkcell.library.entity.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberStatusRepository extends JpaRepository<MemberStatus, Integer> {
}
