package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.service.IRolService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateUserUseCase {

    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;
    @Autowired
    IRolService rolService;
    public User execute(Long id, CreateUserRequest  createUserRequest) {

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
}
