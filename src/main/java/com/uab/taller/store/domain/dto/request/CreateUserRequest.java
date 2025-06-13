package com.uab.taller.store.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public class CreateUserRequest {
    String email;
    String password;
    String name;
    String lastName;
    LocalDateTime AddDate;
    String ci;
    String mobile;
    String address;
    String status;
    Long rolId;
}
