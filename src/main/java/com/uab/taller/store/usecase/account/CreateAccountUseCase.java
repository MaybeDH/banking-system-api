package com.uab.taller.store.usecase.account;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreateAccountUseCase {
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;
    public Account execute(AccountRequest accountRequest){
        User user = userService.getUserById(accountRequest.getUserId());
        if(user == null){
            System.out.println("User not found");
            return null;
        }

        Account account = new Account();
        account.setNumber(generateAccountNumber());
        account.setCurrencyType(accountRequest.getCurrencyType());
        account.setBalance(accountRequest.getBalance());
        account.setUser(user);
        return accountService.save(account);

    }

    public int generateAccountNumber(){
        Random random = new Random();
        return 1000100000 + random.nextInt(99999);

    }
}
