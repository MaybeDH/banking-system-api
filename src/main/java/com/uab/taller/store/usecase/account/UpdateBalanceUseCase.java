package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.dto.request.BalanceRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateBalanceUseCase {
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;

    public ResponseEntity<?> updateAccountBalance(Long id, BalanceRequest balanceRequest) {
        Account account = accountService.getById(id);
        if (account == null || account.isDeleted()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cuenta no encontrada o eliminada");
        }
        if (balanceRequest.getBalance() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El saldo es obligatorio");
        }
        account.setBalance(balanceRequest.getBalance());
        account.setChangeDate(LocalDateTime.now());
        account.setChangeUser("system");
        return ResponseEntity.ok(accountService.save(account));
    }
}
