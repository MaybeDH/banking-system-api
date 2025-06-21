package com.uab.taller.store.service;

import com.uab.taller.store.domain.Profile;

import java.util.List;
import java.util.Optional;

public interface IProfileService {

    List<Profile> getAll();
    Profile saveProfile(Profile profile);
    Profile getProfileById(Long id);
    void deleteProfileById(Long id);

    Profile updateProfile(Long id, Profile profile);

    Optional<Profile> findByCi(String ci);
}
