package com.silkroutestore.payment.repository;

import com.silkroutestore.payment.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, Long> { }
