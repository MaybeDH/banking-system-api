package com.uab.taller.store.usecase.beneficiary;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.BeneficiaryRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IBeneficiaryService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class UpdateUseCase {
    @Autowired
    IBeneficiaryService beneficiaryService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;

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
}
