package com.uab.taller.store.domain.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProfileRequest {
    String name;
    String lastName;
    String ci;
    String mobile;
    String address;
    String status;
}
