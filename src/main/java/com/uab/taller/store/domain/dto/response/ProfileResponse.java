package com.uab.taller.store.domain.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ProfileResponse {
    Long id;
    String name;
    String lastName;
    String ci;
    String mobile;
    String address;
    String status;
}
