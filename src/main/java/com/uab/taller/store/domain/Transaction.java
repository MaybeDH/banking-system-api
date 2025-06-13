package com.uab.taller.store.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long transactionId;

    @ManyToOne
    @JoinColumn(name="source_account_id")
    Account sourceAccount;

    @ManyToOne
    @JoinColumn(name="target_account_id")
    Account targetAccount;

    String transactionType;
    BigDecimal amount;
    LocalDateTime date;
}
