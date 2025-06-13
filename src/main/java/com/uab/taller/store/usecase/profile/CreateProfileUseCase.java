package com.uab.taller.store.usecase.profile;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.dto.request.ProfileRequest;
import com.uab.taller.store.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProfileUseCase {
    @Autowired
    IProfileService profileService;
    public Profile saveProfile(ProfileRequest profileRequest) {
        Profile profile = new Profile();
        profile.setName(profileRequest.getName());
        profile.setLastName(profileRequest.getLastName());
        return profileService.saveProfile(profile);

    }
}
