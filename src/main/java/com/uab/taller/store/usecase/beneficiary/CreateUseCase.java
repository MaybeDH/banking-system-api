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
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class CreateUseCase {
    @Autowired
    IBeneficiaryService beneficiaryService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;
    public Beneficiary createBeneficiary(@RequestBody BeneficiaryRequest beneficiaryRequest){
        User user = userService.getUserById(beneficiaryRequest.getUserId());
        Account account = accountService.getById(beneficiaryRequest.getAccountId());
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAddDate(LocalDateTime.now());
        beneficiary.setAddUser("System");
        beneficiary.setDeleted(false);

        beneficiary.setAlias(beneficiaryRequest.getAlias());
        beneficiary.setAccount(account);
        beneficiary.setUser(user);

        return beneficiaryService.save(beneficiary);
    }
}
