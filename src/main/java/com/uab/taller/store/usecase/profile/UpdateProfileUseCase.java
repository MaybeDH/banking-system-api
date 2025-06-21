package com.uab.taller.store.usecase.profile;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.domain.dto.request.ProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class UpdateProfileUseCase {
    @Autowired
    IProfileService profileService;

    public Profile updateProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
        Profile profile = profileService.getProfileById(id);
        profile.setName(profileRequest.getName());
        profile.setLastName(profileRequest.getLastName());
        profile.setCi(profileRequest.getCi());
        profile.setMobile(profileRequest.getMobile());
        profile.setAddress(profileRequest.getAddress());
        profile.setStatus(profileRequest.getStatus());
        profile.setChangeDate(LocalDateTime.now());
        profile.setChangeUser("system");

        return profileService.updateProfile(id, profile);

    }
}
