package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UpdateAccountUseCase {
    @Autowired
    IAccountService accountService;
    public Account update(Long id, AccountRequest accountRequest) {
        Account account = accountService.getById(id);
        account.setCurrencyType(accountRequest.getCurrencyType());
        account.setBalance(accountRequest.getBalance());
        return accountService.save(account);
    }

}
