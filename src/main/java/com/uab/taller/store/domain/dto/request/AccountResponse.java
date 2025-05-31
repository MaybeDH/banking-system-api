package com.uab.taller.store.domain.dto.request;

import lombok.Getter;

@Getter
public class AccountResponse {
    String number;
    String currencyType;
    double balance;
    Long userId;
}
