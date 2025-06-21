package com.uab.taller.store.usecase.transaction;

import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GetByIdTransactionUseCase {
    @Autowired
    ITransactionService transactionService;
    @Autowired
    IAccountService accountService;
    public Transaction getTransactionById(@PathVariable Long id){
        return transactionService.getById(id);
    }

    public List<Transaction> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.getByAccountId(accountId);
    }
}
