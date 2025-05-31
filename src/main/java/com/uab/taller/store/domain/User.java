package com.uab.taller.store.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String password;

    @OneToOne(fetch = FetchType.EAGER)
            @JoinColumn(name = "profile_id", referencedColumnName = "id")
    Profile profile;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Account> accounts;

}
