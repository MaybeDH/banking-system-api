package com.uab.taller.store.domain.dto.response;

import com.uab.taller.store.domain.Profile;
import lombok.*;



@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    String email;
    ProfileResponse profile;
    String rol;
}
