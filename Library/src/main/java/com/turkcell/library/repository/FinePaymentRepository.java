package com.turkcell.library.repository;

import com.turkcell.library.entity.FinePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinePaymentRepository extends JpaRepository<FinePayment, Integer> {
}
