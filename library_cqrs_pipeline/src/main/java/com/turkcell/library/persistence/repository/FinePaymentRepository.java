package com.turkcell.library.persistence.repository;

import com.turkcell.library.domain.entity.FinePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinePaymentRepository extends JpaRepository<FinePayment, Integer> {
}
