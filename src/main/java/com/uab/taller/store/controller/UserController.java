package com.uab.taller.store.controller;

import com.uab.taller.store.domain.*;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.domain.dto.request.LoginRequest;
import com.uab.taller.store.service.*;
import com.uab.taller.store.usecase.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IBeneficiaryService beneficiaryService;
    @Autowired
    IRolService rolService;
    @Autowired
    UserServiceImp userServiceImp;
    @Operation(
            summary = "Obtener todos los usuarios"
    )
    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }

    @Operation(
            summary = "Obtener un usuario por ID"
    )
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(
            summary = "Eliminar un usuario por ID"
    )
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {

        userService.deleteUserById(id);
    }

    @Operation(
            summary = "Crear un nuevo usuario"
    )
    @PostMapping
    public User saveUser(@RequestBody CreateUserRequest createUserRequest) {

        Profile profile = new Profile();
        profile.setName(createUserRequest.getName());
        profile.setLastName(createUserRequest.getLastName());
        profile.setAddress(createUserRequest.getAddress());
        profile.setCi(createUserRequest.getCi());
        profile.setAddUser(createUserRequest.getAddress());
        profile.setMobile(createUserRequest.getMobile());
        profile = profileService.saveProfile(profile);

        User user = new User();
        user.setPassword(createUserRequest.getPassword());
        user.setEmail(createUserRequest.getEmail());
        user.setProfile(profile);
        Rol rol = rolService.getById(createUserRequest.getRolId());
        user.setRol(rol);
        return userService.saveUser(user);
    }

    @Operation(
            summary = "Actualizar un usuario por ID"
    )
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody CreateUserRequest createUserRequest) {
        User user = userService.getUserById(id);
        Profile profile = user.getProfile();
        profile.setName(createUserRequest.getName());
//        profile.setGender(createUserRequest.getGender());
//        profile.setBirthday(createUserRequest.getBirthday());
        profile.setLastName(createUserRequest.getLastName());
        profile.setCi(createUserRequest.getCi());
        profile.setMobile(createUserRequest.getMobile());
        profile.setAddress(createUserRequest.getAddress());
        profile.setStatus(createUserRequest.getStatus());
        profile.setChangeDate(LocalDateTime.now());
        profile.setChangeUser("");

        profile = profileService.saveProfile(profile);
//        Rol rol = rolService.getRolById(createUserRequest.getRolId());
//        Rol rol = rolService.getProfileById(createUserRequest.getRolId());

        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setProfile(profile);
//        user.setRol(rol);
        user.setChangeDate(LocalDateTime.now());
        user.setChangeUser("");

        return userService.saveUser(user);

    }
//    @Operation(
//            summary = "Obtener un usuario por email"
//    )
//    @GetMapping(value = "/email/{email}")
//    public User getUserByEmail(@PathVariable String email) {
//        return getUserByEmailUseCase.getUserByEmail(email);

//    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userServiceImp.login(loginRequest.getEmail(), loginRequest.getPassword());
        return user
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
