package com.uab.taller.store.service;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BeneficiaryServiceImp implements IBeneficiaryService{
    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    @Override
    public List<Beneficiary> getAll(){
        return beneficiaryRepository.findAll();
    }
    @Override
    public Beneficiary getById(Long id){
        return beneficiaryRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public Beneficiary save(Beneficiary beneficiary){
        return beneficiaryRepository.save(beneficiary);
    }
    @Override
    public void delete(Long id){
        beneficiaryRepository.deleteById(id);
    }
    @Override
    public List<Beneficiary> findByUserId(Long id) {
        return beneficiaryRepository.findByUserId(id);
    }


}
