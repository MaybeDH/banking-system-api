package com.uab.taller.store.domain.dto.response;

import com.uab.taller.store.domain.Profile;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    Long id;
    String email;
    ProfileResponse profile;
    RolResponse rol;
}
