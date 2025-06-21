package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.domain.dto.request.BalanceRequest;
import com.uab.taller.store.domain.dto.response.AccountResponse;
import com.uab.taller.store.domain.dto.response.AccountsByUserResponse;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IUserService;
import com.uab.taller.store.usecase.account.*;
import com.uab.taller.store.usecase.user.GetUserByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;
    @Autowired
    GetAccountsByUserIdUseCase getAccountsByUserIdUseCase;
    @Autowired
    CreateAccountUseCase createAccountUseCase;
    @Autowired
    UpdateAccountUseCase updateAccountUseCase;
    @Autowired
    DeleteAccountUseCase deleteAccountUseCase;
    @Autowired
    GetAccountByIdUseCase getAccountByIdUseCase;
    @Autowired
    UpdateBalanceUseCase updateBalanceUseCase;
    @Autowired
    GetAllAccountsUseCase getAllAccountsUseCase;


    @Operation(
            summary = "Obtener todas las cuentas"
    )
    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return getAllAccountsUseCase.execute().stream()
                .map(this::toAccountResponse)
                .collect(Collectors.toList());
    }

    private AccountResponse toAccountResponse(Account account) {
        if (account == null) return null;
        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .accountNumber(String.valueOf(account.getAccountNumber()))
                .currency(account.getCurrency())
                .type(account.getType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .userId(account.getUser() != null ? account.getUser().getId() : null)
                .build();
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
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return deleteAccountUseCase.delete(id);
    }

    @Operation(
            summary = "Actualizar una cuenta existente por ID"
    )
    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        return updateAccountUseCase.update(id, accountRequest);

    }
    @Operation(
            summary = "Obtener cuentas por id de usuario"
    )
    @GetMapping("/user/{userId}")

    public AccountsByUserResponse getAccountsByUserId(@PathVariable Long userId) {
        return getAccountsByUserIdUseCase.getAccountsByUserId(userId);
    }

    @PatchMapping("/{id}/balance")
    public ResponseEntity<?> updateAccountBalance(@PathVariable Long id, @RequestBody BalanceRequest balanceRequest) {
        return updateBalanceUseCase.updateAccountBalance(id, balanceRequest);
    }

}
