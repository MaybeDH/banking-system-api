package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.BeneficiaryRequest;
import com.uab.taller.store.repository.BeneficiaryRepository;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IBeneficiaryService;
import com.uab.taller.store.service.IUserService;
import com.uab.taller.store.usecase.account.GetAccountByIdUseCase;
import com.uab.taller.store.usecase.account.GetAllAccountsUseCase;
import com.uab.taller.store.usecase.beneficiary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin

@RestController
@RequestMapping(value = "beneficiary")
public class BenificiaryController {
    @Autowired
    IBeneficiaryService beneficiaryService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;

    @Autowired
    CreateUseCase createUseCase;
    @Autowired
    DeleteUseCase deleteUseCase;
    @Autowired
    GetAllUseCase getAllUseCase;
    @Autowired
    UpdateUseCase updateUseCase;
    @Autowired
    GetByIdUseCase getByIdUseCase;
    @Autowired
    GetBeneficiaryByUserIdUseCase getBeneficiaryByUserIdUseCase;


    @GetMapping
    public List<Beneficiary> getAllBeneficiaries() {
        return getAllUseCase.getAllBeneficiaries();

    }
    @DeleteMapping("/{id}")
    public void deleteBeneficiary(@PathVariable Long id){
        deleteUseCase.deleteBeneficiary(id);
    }
    @GetMapping("/{id}")
    public Beneficiary getBeneficiaryById(@PathVariable Long id){
        return getByIdUseCase.getBeneficiaryById(id);
    }
    @PostMapping
    public Beneficiary createBeneficiary(@RequestBody BeneficiaryRequest beneficiaryRequest){
        return createUseCase.createBeneficiary(beneficiaryRequest);
    }
    @PutMapping("/{id}")
    public Beneficiary updateBeneficiary(@PathVariable Long id, @RequestBody BeneficiaryRequest beneficiaryRequest){
        return updateUseCase.updateBeneficiary(id, beneficiaryRequest);

    }

    @GetMapping("/user/{userId}")
    public List<Beneficiary> getBeneficiariesByUserId(@PathVariable Long userId) {
        return getBeneficiaryByUserIdUseCase.getBeneficiariesByUserId(userId);
    }
}
