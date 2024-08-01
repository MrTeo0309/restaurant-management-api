package com.res.main.repository;

import com.res.main.model.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionsEntity, Long> {
}
