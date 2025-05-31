package com.uab.taller.store.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileRequest {
    String name;
    String lastName;
    LocalDate birthday;
    String gender;
}
