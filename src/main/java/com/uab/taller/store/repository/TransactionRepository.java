package com.uab.taller.store.repository;

import com.uab.taller.store.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findBySourceAccountAccountIdOrTargetAccountAccountId(Long sourceId, Long targetId);

}
