package com.uab.taller.store.service;

import com.uab.taller.store.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAll();

    User getUserById(Long id);
    void deleteUserById(Long id);
    User saveUser(User user);
    Optional<User> getUserByEmail(String email);
}
