package com.uab.taller.store.domain.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsByUserResponse {
    private List<AccountResponse> accounts;
    private BigDecimal totalBalance;
    private BigDecimal totalBalanceUS;
}
