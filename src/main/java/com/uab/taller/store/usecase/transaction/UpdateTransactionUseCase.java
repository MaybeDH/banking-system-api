package com.uab.taller.store.usecase.transaction;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.domain.dto.request.TransactionRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class UpdateTransactionUseCase {
    @Autowired
    ITransactionService transactionService;
    @Autowired
    IAccountService accountService;
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest){
        Transaction transaction = transactionService.getById(id);
        Account sourceAccount = accountService.getById(transactionRequest.getSourceAccountId());
        Account targetAaccount = null;
        if(transactionRequest.getTargetAccountId()!=null){
            targetAaccount = accountService.getById(transactionRequest.getTargetAccountId());
        }
        transaction.setTargetAccount(targetAaccount);
        transaction.setSourceAccount(sourceAccount);
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDate(LocalDateTime.now());
        return transactionService.save(transaction);

    }
}
