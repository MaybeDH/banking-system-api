package com.uab.taller.store.repository;

import com.uab.taller.store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findByEmail(String email);

    /*
    User authenticate(String email, String password);*/
}
