package com.uab.taller.store.domain.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountResponse {
    String accountNumber;
    String currency;
    String type;
//    String currencyType;
//    double balance;
    BigDecimal balance;
    String status;
//    Long userId;
}
