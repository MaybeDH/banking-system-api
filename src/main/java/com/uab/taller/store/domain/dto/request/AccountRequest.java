package com.uab.taller.store.domain.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    String currencyType;
    double balance;
    Long userId;
}
