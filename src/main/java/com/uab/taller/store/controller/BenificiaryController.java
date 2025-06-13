package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.BeneficiaryRequest;
import com.uab.taller.store.repository.BeneficiaryRepository;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IBeneficiaryService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "beneficiary")
public class BenificiaryController {
    @Autowired
    IBeneficiaryService beneficiaryService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;

    @GetMapping
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryService.getAll();
    }
    @DeleteMapping("/{id}")
    public void deleteBeneficiary(@PathVariable Long id){
        Beneficiary beneficiary =getBeneficiaryById(id);
        beneficiary.setDeleted(true);
        beneficiary.setChangeUser("system");
        beneficiaryService.save(beneficiary);
    }
    @GetMapping("/{id}")
    public Beneficiary getBeneficiaryById(@PathVariable Long id){
        return beneficiaryService.getById(id);
    }
    @PostMapping
    public Beneficiary createBeneficiary(@RequestBody BeneficiaryRequest beneficiaryRequest){
        User user = userService.getUserById(beneficiaryRequest.getUserId());
        Account account = accountService.getById(beneficiaryRequest.getAccountId());

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAddDate(LocalDateTime.now());
        beneficiary.setAddUser("System");
        beneficiary.setChangeDate(LocalDateTime.now());
        beneficiary.setChangeUser("System");
        beneficiary.setDeleted(false);
        beneficiary.setAccount(account);
        beneficiary.setUser(user);

        return beneficiaryService.save(beneficiary);
    }
    @PutMapping("/{id}")
    public Beneficiary updateBeneficiary(@PathVariable Long id, @RequestBody BeneficiaryRequest beneficiaryRequest){
        Beneficiary beneficiary = beneficiaryService.getById(id);
        User user = userService.getUserById(beneficiaryRequest.getUserId());
        Account account = accountService.getById(beneficiaryRequest.getAccountId());

        beneficiary.setChangeDate(LocalDateTime.now());
        beneficiary.setChangeUser("system");
        beneficiary.setUser(user);
        beneficiary.setAccount(account);

        return beneficiaryService.save(beneficiary);

    }

//    @GetMapping("/user/{userId}")
//    public List<Beneficiary> getBeneficiariesByUserId(@PathVariable Long userId) {
//        return beneficiaryService.getByUserId(userId);
//    }
}
