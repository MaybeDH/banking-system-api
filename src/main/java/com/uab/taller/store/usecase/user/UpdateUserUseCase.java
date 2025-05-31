package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.service.IAccountService;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {

    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;

    public User execute(Long id, CreateUserRequest  createUserRequest) {
        User user = userService.getUserById(id);
        Profile profile = user.getProfile();
        profile.setName(createUserRequest.getName());
        profile.setGender(createUserRequest.getGender());
        profile.setBirthday(createUserRequest.getBirthday());
        profile.setLastName(createUserRequest.getLastName());

        profile = profileService.saveProfile(profile);

        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setProfile(profile);

        return userService.saveUser(user);
    }
}
