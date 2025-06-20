package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class UpdateAccountUseCase {
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;
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

}
