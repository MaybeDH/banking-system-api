package com.uab.taller.store.service;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.repository.ProfileRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ProfileServiceImp implements IProfileService{

    ProfileRepository profileRepository;
    public ProfileServiceImp(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }
    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }
    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @Override
    public void deleteProfileById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile updateProfile(Long id, Profile profile) {
        Profile existingProfile = getProfileById(id);
        existingProfile.setName(profile.getName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setBirthday(profile.getBirthday());
        existingProfile.setGender(profile.getGender());
        return profileRepository.save(existingProfile);
    }

}
