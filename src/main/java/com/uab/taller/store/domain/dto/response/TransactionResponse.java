package com.uab.taller.store.domain.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    String transactionType;
    BigDecimal amount;
    LocalDateTime date;
    String sourceAccount;
    String targetAccount;
}
