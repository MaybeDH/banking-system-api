package com.uab.taller.store.service;

import com.uab.taller.store.domain.Transaction;

import java.util.List;

public interface ITransactionService {
    List<Transaction> getAll();
    Transaction save(Transaction transaction);
    Transaction getById(Long id);
    void deleteById(Long id);
    List<Transaction> getByAccountId(Long accountId);

}
