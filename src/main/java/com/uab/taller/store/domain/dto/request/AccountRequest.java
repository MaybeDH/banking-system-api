package com.uab.taller.store.domain.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {

    String currency;
    String type;
    BigDecimal balance;
    String status;
//    double balance;
    Long userId;
}
