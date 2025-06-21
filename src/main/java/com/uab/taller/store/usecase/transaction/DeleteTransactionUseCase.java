package com.uab.taller.store.usecase.transaction;

import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class DeleteTransactionUseCase {

    @Autowired
    ITransactionService transactionService;
    @Autowired
    IAccountService accountService;
    public void delete(@PathVariable Long id){
        transactionService.deleteById(id);
    }
}
