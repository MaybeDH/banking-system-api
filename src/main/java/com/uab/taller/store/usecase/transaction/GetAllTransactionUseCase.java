package com.uab.taller.store.usecase.transaction;

import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTransactionUseCase {

    @Autowired
    ITransactionService transactionService;
    @Autowired
    IAccountService accountService;
    public List<Transaction> getAll(){
        return transactionService.getAll();
    }
}
