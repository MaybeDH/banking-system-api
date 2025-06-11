package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.usecase.account.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/Accounts")
public class AccountController {

    @Autowired
    GetAllAccountsUseCase getAllAccountsUseCase;

    @Autowired
    CreateAccountUseCase createAccountUseCase;

    @Autowired
    DeleteAccountUseCase deleteAccountUseCase;

    @Autowired
    UpdateAccountUseCase updateAccountUseCase;

    @Autowired
    GetAccountByIdUseCase getAccountByIdUseCase;

    @Operation(
            summary = "Obtener todas las cuentas"
    )
    @GetMapping
    public List<Account> getAllAccounts() {
        return getAllAccountsUseCase.execute();
    }

    @Operation(
            summary = "Obtener una cuenta por ID"
    )
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return getAccountByIdUseCase.execute(id);
    }

    @Operation(
            summary = "Crear una nueva cuenta"
    )
    @PostMapping
    public Account createAccount(@RequestBody AccountRequest accountRequest) {
        return createAccountUseCase.execute(accountRequest);
    }

    @Operation(
            summary = "Eliminar una cuenta por ID"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteAccountUseCase.execute(id);
    }

    @Operation(
            summary = "Actualizar una cuenta existente por ID"
    )
    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        return updateAccountUseCase.update(id, accountRequest);
    }
}
