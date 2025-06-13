package com.uab.taller.store.domain.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficiaryResponse {
    String accountNumber;
    String currency;
    String type;
    String status;
}
