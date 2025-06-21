package com.uab.taller.store.usecase.beneficiary;

import com.uab.taller.store.domain.Beneficiary;
import com.uab.taller.store.repository.BeneficiaryRepository;
import com.uab.taller.store.service.IBeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class GetBeneficiaryByUserIdUseCase {
    @Autowired
    IBeneficiaryService beneficiaryService;
    public List<Beneficiary> getBeneficiariesByUserId(@PathVariable Long userId) {
        return beneficiaryService.findByUserId(userId);
    }
}
