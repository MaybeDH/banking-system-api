package com.uab.taller.store.repository;

import com.uab.taller.store.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByUserId(Long id);

}
