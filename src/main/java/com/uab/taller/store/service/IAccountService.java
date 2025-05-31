package com.uab.taller.store.service;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.dto.request.AccountRequest;

import java.util.List;

public interface IAccountService {
    public List<Account> getAll();
    public Account save(Account account);
    public void delete(Long id);
    public Account getById(Long id);

}
