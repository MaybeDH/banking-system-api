package com.uab.taller.store.domain.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    String email;
    String password;
    ProfileRequest profile;
    Long roleId;
//    String name;
//    String lastName;
//    String email;
//    String password;

}
