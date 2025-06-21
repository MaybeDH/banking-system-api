package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CreateAccountUseCase {
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;
    public Account execute(AccountRequest accountRequest){
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
}
