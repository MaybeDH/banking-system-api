package com.uab.taller.store.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long beneficiaryId;
    LocalDateTime addDate;
    String addUser;
    LocalDateTime changeDate;
    String changeUser;
    boolean deleted;

    String alias;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

}

