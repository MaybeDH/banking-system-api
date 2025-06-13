package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.domain.dto.request.TransactionRequest;
import com.uab.taller.store.repository.TransactionRepository;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController {
    @Autowired
    ITransactionService transactionService;
    @Autowired
    IAccountService accountService;
    @GetMapping
    public List<Transaction> getAll(){
        return transactionService.getAll();
    }
    @DeleteMapping("/{id}")
    public void deleteAll(@PathVariable Long id){
        transactionService.deleteById(id);
    }
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id){
        return transactionService.getById(id);
    }
    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest transactionRequest){
        Account sourceAccount = accountService.getById(transactionRequest.getSourceAccountId());
        Account targetAccount = null;
        if(transactionRequest.getTargetAccountId()!=null){
            targetAccount = accountService.getById(transactionRequest.getTargetAccountId());
        }


        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDate(LocalDateTime.now());

        // Lógica adicional opcional según el tipo de transacción:
        if ("Retiro".equalsIgnoreCase(transaction.getTransactionType())) {
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(transaction.getAmount()));
        } else if ("Depósito".equalsIgnoreCase(transaction.getTransactionType())) {
            sourceAccount.setBalance(sourceAccount.getBalance().add(transaction.getAmount()));
        } else if ("Transferencia".equalsIgnoreCase(transaction.getTransactionType()) && targetAccount != null) {
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(transaction.getAmount()));
            targetAccount.setBalance(targetAccount.getBalance().add(transaction.getAmount()));
        }

        // Guardar cambios en cuentas
        accountService.save(sourceAccount);
        if (targetAccount != null) {
            accountService.save(targetAccount);
        }
        return transactionService.save(transaction);

    }

    @PutMapping("/{id}")
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
    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.getByAccountId(accountId);
    }


}
