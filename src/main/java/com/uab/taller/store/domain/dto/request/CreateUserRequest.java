package com.uab.taller.store.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CreateUserRequest {
    @Email
    @NotBlank
    String email;
    @NotBlank
    @Size(min = 4)
    String password;
    @NotBlank
    String name;
    @NotBlank
    String lastName;
    @NotBlank
    String ci;
    String mobile;
    String address;
    @NotBlank
    String status;
    Long rolId;
    LocalDateTime AddDate;
}
