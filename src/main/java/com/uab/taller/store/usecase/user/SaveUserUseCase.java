package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.domain.dto.request.UserRequest;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUserUseCase {
    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;
    public User save(CreateUserRequest createUserRequest) {
        Profile profile = new Profile();
        profile.setName(createUserRequest.getName());
        profile.setGender(createUserRequest.getGender());
        profile.setBirthday(createUserRequest.getBirthday());
        profile.setLastName(createUserRequest.getLastName());
        profile = profileService.saveProfile(profile);

        User user = new User();
        user.setPassword(createUserRequest.getPassword());
        user.setEmail(createUserRequest.getEmail());
        user.setProfile(profile);
        return userService.saveUser(user);
    }
}
