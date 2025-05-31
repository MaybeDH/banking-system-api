package com.uab.taller.store.usecase.profile;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.domain.dto.request.ProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProfileUseCase {
    @Autowired
    IProfileService profileService;

    public Profile updateProfiles(Long id, ProfileRequest profileRequest) {
        Profile profile = profileService.getProfileById(id);
        profile.setName(profileRequest.getName());
        profile.setLastName(profileRequest.getLastName());
        profile.setBirthday(profileRequest.getBirthday());
        profile.setGender(profileRequest.getGender());
        return profileService.updateProfile(id, profile);
    }
}
