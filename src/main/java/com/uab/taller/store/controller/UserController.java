package com.uab.taller.store.controller;

import com.uab.taller.store.domain.*;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.domain.dto.request.LoginRequest;
import com.uab.taller.store.domain.dto.response.ProfileResponse;
import com.uab.taller.store.domain.dto.response.RolResponse;
import com.uab.taller.store.domain.dto.response.UserResponse;
import com.uab.taller.store.service.*;
import com.uab.taller.store.usecase.user.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController {
    @Autowired
    DeleteUserUseCase deleteUserUseCase;
    @Autowired
    GetUserByEmailCase getUserByEmailCase;
    @Autowired
    GetUserByIdUseCase getUserByIdCase;
    @Autowired
    GetUsersUseCase getUsersUseCase;
    @Autowired
    SaveUserUseCase saveUserUseCase;
    @Autowired
    UpdateUserUseCase updateUserUseCase;
    @Autowired
    loginUseCase loginUseCase;


    @Operation(
            summary = "Obtener todos los usuarios"
    )
    @GetMapping
    public List<User> getUsers() {
        return getUsersUseCase.getUsers();

    }

    @Operation(
            summary = "Obtener un usuario por ID"
    )
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return getUserByIdCase.getUserById(id);
    }

    @Operation(
            summary = "Eliminar un usuario por ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return deleteUserUseCase.deleteUserById(id);
    }

    @Operation(
            summary = "Crear un nuevo usuario"
    )
    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return  saveUserUseCase.saveUser(createUserRequest);
    }

    @Operation(
            summary = "Actualizar un usuario por ID"
    )
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody CreateUserRequest createUserRequest) {
        return updateUserUseCase.updateUser(id, createUserRequest);

    }
//    @Operation(
//            summary = "Obtener un usuario por email"
//    )
//    @GetMapping(value = "/email/{email}")
//    public User getUserByEmail(@PathVariable String email) {
//        return getUserByEmailUseCase.getUserByEmail(email);

//    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        return loginUseCase.login(loginRequest);
    }




}
