package com.uab.taller.store.usecase.profile;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.dto.request.ProfileRequest;
import com.uab.taller.store.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class CreateProfileUseCase {
    @Autowired
    IProfileService profileService;
    public Profile saveProfile(@RequestBody ProfileRequest profileRequest) {
        Profile profile = new Profile();
        profile.setName(profileRequest.getName());
        profile.setLastName(profileRequest.getLastName());
        profile.setCi(profileRequest.getCi());
        profile.setMobile(profileRequest.getMobile());
        profile.setAddress(profileRequest.getAddress());
        profile.setStatus(profileRequest.getStatus());
        profile.setAddDate(LocalDateTime.now());
        profile.setAddUser("system");
        profile.setDeleted(false);
        return profileService.saveProfile(profile);
    }
    }
