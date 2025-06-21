package com.uab.taller.store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long profileId;

    LocalDateTime addDate;
    String addUser;
    LocalDateTime changeDate;
    String changeUser;
    boolean deleted;

    String name;
    String lastName;
    String ci;
    String mobile;
    String address;
    String status;
}

