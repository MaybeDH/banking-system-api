package com.uab.taller.store.domain.dto.request;

import lombok.*;

@Getter
@Setter
public class BeneficiaryRequest {
    String alias;
    Long userId;
    Long accountId;
}
