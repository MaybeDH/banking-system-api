package com.uab.taller.store.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    LocalDateTime addDate;
    String addUser;
    LocalDateTime changeDate;
    String changeUser;
    boolean deleted;

    @Column(unique = true)
    int accountNumber;


    String currency;
    String type;
    BigDecimal balance;
    String status;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;
}




