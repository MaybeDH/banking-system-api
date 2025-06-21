package com.uab.taller.store.service;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.dto.request.AccountRequest;
import com.uab.taller.store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImp implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);

    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> findByUserId(Long id){
        return accountRepository.findByUserId(id);
    }



}
