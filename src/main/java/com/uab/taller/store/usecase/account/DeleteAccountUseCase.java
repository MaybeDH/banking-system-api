package com.uab.taller.store.usecase.account;

import com.uab.taller.store.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteAccountUseCase {
    @Autowired
    IAccountService accountService;
    public  void execute(Long id){
        accountService.delete(id);
    }
}
