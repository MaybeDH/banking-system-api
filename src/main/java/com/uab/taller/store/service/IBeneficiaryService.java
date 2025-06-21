package com.uab.taller.store.service;

import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.domain.Transaction;

import java.util.List;

public interface IBeneficiaryService {
    List<Beneficiary> getAll();
    Beneficiary getById(Long id);
    Beneficiary  save(Beneficiary beneficiary);
    void delete(Long id);
    List<Beneficiary> findByUserId(Long id);

}
