package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.domain.dto.response.AccountResponse;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IUserService;
import com.uab.taller.store.usecase.account.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(
            summary = "Obtener todas las cuentas"
    )
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    @Operation(
            summary = "Obtener una cuenta por ID"
    )
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @Operation(
            summary = "Crear una nueva cuenta"
    )
    @PostMapping
    public Account createAccount(@RequestBody AccountRequest accountRequest) {
        User user = userService.getUserById(accountRequest.getUserId());
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setCurrency(accountRequest.getCurrency());
        account.setType(accountRequest.getType());
        account.setBalance(accountRequest.getBalance());
        account.setStatus(accountRequest.getStatus());
        account.setUser(user);

        account.setAddDate(LocalDateTime.now());
        account.setAddUser("system");
        account.setDeleted(false);
        return accountService.save(account);
    }
    public int generateAccountNumber(){
        Random random = new Random();
        return 1000000000 + random.nextInt(99999);

    }

    @Operation(
            summary = "Eliminar una cuenta por ID"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Account account = accountService.getById(id);
        account.setDeleted(true);
        account.setChangeDate(LocalDateTime.now());
        account.setChangeUser("system");
        accountService.delete(id);
    }

    @Operation(
            summary = "Actualizar una cuenta existente por ID"
    )
    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        Account account = accountService.getById(id);
        User user = userService.getUserById(accountRequest.getUserId());

        account.setCurrency(accountRequest.getCurrency());
        account.setType(accountRequest.getType());
        account.setBalance(accountRequest.getBalance());
        account.setStatus(accountRequest.getStatus());
        account.setUser(user);
        account.setChangeDate(LocalDateTime.now());
        account.setChangeUser("system");
        return accountService.save(account);

    }
//    @GetMapping("/user/{userId}")
//    public List<Account> getAccountsByUserId(@PathVariable Long userId) {
//        return accountService.findByUserId(userId);
//    }


}
