package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllAccountsUseCase {

    @Autowired
    IAccountService accountService;
    public List<Account> execute(){
        return accountService.getAll();
    }
}
