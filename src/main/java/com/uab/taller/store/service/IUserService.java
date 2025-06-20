package com.uab.taller.store.service;

import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.UserRequest;
import com.uab.taller.store.domain.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAll();
    User getUserById(Long id);
    void deleteUserById(Long id);
    User saveUser(User user);
    Optional<User> getUserByEmail(String email);
//    User authenticate(String email, String password);

    Optional<User> login(String email, String password);
}
