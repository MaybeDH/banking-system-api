package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.dto.request.ProfileRequest;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.usecase.profile.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin

@RestController
@RequestMapping(value="/profiles")
public class ProfileController {
    @Autowired
    IProfileService profileService;
    @Operation(
            summary = "Obtener todas los perfiles"
    )
    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.getAll();
    }
    @Operation(
            summary = "Crear una nuevo perfil"
    )
    @PostMapping
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
    @Operation(
            summary = "Obtener un perfil por ID"
    )
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Long id) {

        return profileService.getProfileById(id);
    }
    @Operation(
            summary = "Eliminar un perfil por ID"
    )
    @DeleteMapping("/{id}")
    public void deleteProfileById(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        profile.setDeleted(true);
        profile.setChangeDate(LocalDateTime.now());
        profile.setChangeUser("system");
        profileService.saveProfile(profile);

    }
    @Operation(
            summary = "Actualizar un perfil existente por ID"
    )
    @PutMapping("/{id}")
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
