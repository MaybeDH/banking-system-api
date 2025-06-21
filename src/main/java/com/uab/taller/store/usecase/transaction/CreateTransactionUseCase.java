package com.uab.taller.store.usecase.transaction;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.domain.dto.request.TransactionRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class CreateTransactionUseCase {
    @Autowired
    ITransactionService transactionService;
    @Autowired
    IAccountService accountService;
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

}
