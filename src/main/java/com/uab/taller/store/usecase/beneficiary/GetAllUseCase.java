package com.uab.taller.store.usecase.beneficiary;

import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IBeneficiaryService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUseCase {
    @Autowired
    IBeneficiaryService beneficiaryService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IUserService userService;
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryService.getAll();
    }
}
