package com.uab.taller.store.domain.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolResponse {
    Long id;
    String name;
    String description;
}
