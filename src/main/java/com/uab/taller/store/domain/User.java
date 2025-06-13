package com.uab.taller.store.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime addDate;
    String addUser;
    LocalDateTime changeDate;
    String changeUser;
    boolean deleted;

    String email;
    String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    Profile profile;

//    @OneToOne(fetch = FetchType.EAGER)
//            @JoinColumn(name = "profile_id", referencedColumnName = "id")
//    Profile profile;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Account> accounts;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    Rol rol;

}
