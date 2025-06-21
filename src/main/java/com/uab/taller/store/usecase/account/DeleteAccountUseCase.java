package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Service
public class DeleteAccountUseCase {
    @Autowired
    IAccountService accountService;
    public  void execute(Long id){
        accountService.delete(id);
    }
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Account account = accountService.getById(id);
        if (account == null || account.isDeleted()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cuenta no encontrada o ya eliminada");
        }
        account.setDeleted(true);
        account.setChangeDate(LocalDateTime.now());
        account.setChangeUser("system");
        accountService.save(account);
        return ResponseEntity.ok("Cuenta eliminada correctamente (soft delete)");
    }

}
