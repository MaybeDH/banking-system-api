package com.uab.taller.store.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class CreateUserRequest {

    String email;
    String password;
    String name;
    String lastName;
    String gender;
    LocalDate birthday;
}
