package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class UpdateUserUseCase {

    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;
    @Autowired
    IRolService rolService;
    @Autowired
    UserServiceImp userServiceImp;
    public User updateUser(@PathVariable Long id, @RequestBody CreateUserRequest createUserRequest) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        // // Validar unicidad de email (excepto el propio usuario)
        if (!user.getEmail().equals(createUserRequest.getEmail()) && userService.getUserByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado por otro usuario");
        }
        // Validar unicidad de CI (excepto el propio perfil)
        Profile profile = user.getProfile();
        if (profile == null) {
            profile = new Profile();
        }
        if (!profile.getCi().equals(createUserRequest.getCi()) && profileService.findByCi(createUserRequest.getCi()).isPresent()) {
            throw new RuntimeException("El CI ya está registrado por otro perfil");
        }
        // Actualizar perfil
        profile.setName(createUserRequest.getName());
        profile.setLastName(createUserRequest.getLastName());
        profile.setCi(createUserRequest.getCi());
        profile.setMobile(createUserRequest.getMobile());
        profile.setAddress(createUserRequest.getAddress());
        profile.setStatus(createUserRequest.getStatus());
        profile.setChangeDate(LocalDateTime.now());
        profile.setChangeUser("system");
        profile = profileService.saveProfile(profile);
        // Actualizar rol
        Rol rol = rolService.getById(createUserRequest.getRolId());
        // Actualizar usuario
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setProfile(profile);
        user.setRol(rol);
        user.setChangeDate(LocalDateTime.now());
        user.setChangeUser("system");
        return userService.saveUser(user);
    }
}
