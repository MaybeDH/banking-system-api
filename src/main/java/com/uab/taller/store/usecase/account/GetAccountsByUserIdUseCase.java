package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.dto.response.AccountResponse;
import com.uab.taller.store.domain.dto.response.AccountsByUserResponse;
import com.uab.taller.store.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAccountsByUserIdUseCase {
    @Autowired
    IAccountService accountService;
    public List<Account> execute(Long userId){
        return accountService.findByUserId(userId);
    }
    public AccountsByUserResponse getAccountsByUserId(@PathVariable Long userId) {
        List<AccountResponse> accounts = execute(userId)
                .stream()
                .map(this::toAccountResponse)
                .collect(Collectors.toList());
        java.math.BigDecimal totalBalance = accounts.stream()
                .filter(a -> a.getCurrency() != null && a.getCurrency().equalsIgnoreCase("BS"))
                .map(AccountResponse::getBalance)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        java.math.BigDecimal totalBalanceUS = accounts.stream()
                .filter(a -> a.getCurrency() != null && a.getCurrency().equalsIgnoreCase("USD"))
                .map(AccountResponse::getBalance)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        return AccountsByUserResponse.builder()
                .accounts(accounts)
                .totalBalance(totalBalance)
                .totalBalanceUS(totalBalanceUS)
                .build();
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
                .build();
    }

}
