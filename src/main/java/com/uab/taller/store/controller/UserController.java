package com.uab.taller.store.controller;

import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.usecase.user.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    GetUsersUseCase getUsersUseCase;

    @Autowired
    GetUserByIdUseCase getUserByIdUseCase;

    @Autowired
    DeleteUserUseCase deleteUserUseCase;

    @Autowired
    SaveUserUseCase saveUserUseCase;

    @Autowired
    GetUserByEmailCase getUserByEmailUseCase;

    @Autowired
    UpdateUserUseCase updateUserUseCase;

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
        return getUserByIdUseCase.getUserById(id);
    }

    @Operation(
            summary = "Eliminar un usuario por ID"
    )
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        deleteUserUseCase.deleteUserById(id);
    }

    @Operation(
            summary = "Crear un nuevo usuario"
    )
    @PostMapping
    public User saveUser(@RequestBody CreateUserRequest createUserRequest) {
        return saveUserUseCase.save(createUserRequest);
    }

    @Operation(
            summary = "Obtener un usuario por email"
    )
    @GetMapping(value = "/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return getUserByEmailUseCase.getUserByEmail(email);
    }

    @Operation(
            summary = "Actualizar un usuario por ID"
    )
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody CreateUserRequest createUserRequest) {
        return updateUserUseCase.execute(id, createUserRequest);
    }
}
