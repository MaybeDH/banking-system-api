package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.domain.dto.request.TransactionRequest;
import com.uab.taller.store.repository.TransactionRepository;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.ITransactionService;
import com.uab.taller.store.usecase.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@CrossOrigin
@RestController
@RequestMapping(value = "transaction")
public class TransactionController {
    @Autowired
    ITransactionService transactionService;
    @Autowired
    IAccountService accountService;
    @Autowired
    CreateTransactionUseCase createTransactionUseCase;
    @Autowired
    DeleteTransactionUseCase deleteTransactionUseCase;
    @Autowired
    UpdateTransactionUseCase updateTransactionUseCase;
    @Autowired
    GetAllTransactionUseCase getAllTransactionUseCase;
    @Autowired
    GetByIdTransactionUseCase getByIdTransactionUseCase;


    @GetMapping
    public List<Transaction> getAll(){
        return getAllTransactionUseCase.getAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deleteTransactionUseCase.delete(id);
    }
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id){
        return getByIdTransactionUseCase.getTransactionById(id);
    }
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest transactionRequest){
        // Validar existencia de cuenta origen
        Account sourceAccount = accountService.getById(transactionRequest.getSourceAccountId());
        if (sourceAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta origen no existe");
        }
        // Validar monto positivo
        if (transactionRequest.getAmount() == null || transactionRequest.getAmount().signum() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El monto debe ser positivo");
        }
        Account targetAccount = null;
        if (transactionRequest.getTargetAccountId() != null) {
            targetAccount = accountService.getById(transactionRequest.getTargetAccountId());
            if (targetAccount == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta destino no existe");
            }
            // No permitir transferencias a la misma cuenta
            if (transactionRequest.getSourceAccountId().equals(transactionRequest.getTargetAccountId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede transferir a la misma cuenta");
            }
            // Validar que ambas cuentas tengan la misma moneda
            if (!sourceAccount.getCurrency().equalsIgnoreCase(targetAccount.getCurrency())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se permite transferir entre cuentas de distinta moneda");
            }
        }
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDate(LocalDateTime.now());

        // Validar saldo suficiente para retiro o transferencia
        if ("Retiro".equalsIgnoreCase(transaction.getTransactionType()) ||
            ("Transferencia".equalsIgnoreCase(transaction.getTransactionType()) && targetAccount != null)) {
            if (sourceAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente en la cuenta origen");
            }
        }

        // Lógica de movimiento de saldo
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
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transaction));
    }

    @PostMapping("/currency")
    public ResponseEntity<?> createTransactionCurrency(@RequestBody TransactionRequest transactionRequest){
        Account sourceAccount = accountService.getById(transactionRequest.getSourceAccountId());
        if (sourceAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta origen no existe");
        }
        Account targetAccount = null;
        if (transactionRequest.getTargetAccountId() != null) {
            targetAccount = accountService.getById(transactionRequest.getTargetAccountId());
            if (targetAccount == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta destino no existe");
            }
            if (transactionRequest.getSourceAccountId().equals(transactionRequest.getTargetAccountId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede transferir a la misma cuenta");
            }
            // Si las monedas son distintas, hacer conversión
            if (!sourceAccount.getCurrency().equalsIgnoreCase(targetAccount.getCurrency())) {
                // Ejemplo simple: 1 USD = 7 BS (esto debería venir de un servicio/configuración)
                java.math.BigDecimal tasa = java.math.BigDecimal.valueOf(7);
                java.math.BigDecimal montoConvertido = transactionRequest.getAmount();
                if (sourceAccount.getCurrency().equalsIgnoreCase("USD") && targetAccount.getCurrency().equalsIgnoreCase("BS")) {
                    montoConvertido = transactionRequest.getAmount().multiply(tasa);
                } else if (sourceAccount.getCurrency().equalsIgnoreCase("BS") && targetAccount.getCurrency().equalsIgnoreCase("USD")) {
                    montoConvertido = transactionRequest.getAmount().divide(tasa, 2, java.math.RoundingMode.HALF_UP);
                }
                // Validar saldo suficiente en la cuenta origen (siempre en la moneda de la cuenta origen)
                if (sourceAccount.getBalance().compareTo(transactionRequest.getAmount()) < 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente en la cuenta origen");
                }
                // Actualizar balances SOLO si hay saldo suficiente
                sourceAccount.setBalance(sourceAccount.getBalance().subtract(transactionRequest.getAmount()));
                targetAccount.setBalance(targetAccount.getBalance().add(montoConvertido));
                accountService.save(sourceAccount);
                accountService.save(targetAccount);
                Transaction transaction = new Transaction();
                transaction.setSourceAccount(sourceAccount);
                transaction.setTargetAccount(targetAccount);
                transaction.setTransactionType(transactionRequest.getTransactionType());
                transaction.setAmount(transactionRequest.getAmount());
                transaction.setDate(LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transaction));
            }
        }
        // Si las monedas son iguales, reutilizar createTransaction
        return this.createTransaction(transactionRequest);
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
