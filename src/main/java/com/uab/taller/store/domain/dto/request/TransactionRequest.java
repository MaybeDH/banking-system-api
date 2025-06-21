package com.uab.taller.store.domain.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    Long sourceAccountId;
    Long targetAccountId;
    String transactionType;
    BigDecimal amount;
}
