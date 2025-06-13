package com.uab.taller.store.service;

import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionServiceImp implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }
    @Override
    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);
    }
    @Override
    public Transaction getById(Long id){
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public void deleteById(Long id){
        transactionRepository.deleteById(id);
    }
    @Override
    public List<Transaction> getByAccountId(Long accountId) {
        return transactionRepository.findBySourceAccountAccountIdOrTargetAccountAccountId(accountId, accountId);
    }

}
