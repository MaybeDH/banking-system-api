package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.dto.request.ProfileRequest;
import com.uab.taller.store.usecase.profile.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/profiles")
public class ProfileController {

    @Autowired
    GetAllProfilesUseCase getAllProfilesUseCase;
    @Autowired
    CreateProfileUseCase createProfileUseCase;
    @Autowired
    DeleteProfileUseCase deleteProfileUseCase;
    @Autowired
    GetProfileByIdUseCase getProfileByIdUseCase;
    @Autowired
    UpdateProfileUseCase updateProfileUseCase;

    @Operation(
            summary = "Obtener todas los perfiles"
    )
    @GetMapping
    public List<Profile> getProfiles() {
        return  getAllProfilesUseCase.getProfiles();
    }
    @Operation(
            summary = "Crear una nuevo perfil"
    )
    @PostMapping
    public Profile saveProfile(@RequestBody ProfileRequest profileRequest) {
        return createProfileUseCase.saveProfile(profileRequest);
    }
    @Operation(
            summary = "Obtener un perfil por ID"
    )
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Long id) {
        return getProfileByIdUseCase.getProfileById(id);
    }
    @Operation(
            summary = "Eliminar un perfil por ID"
    )
    @DeleteMapping("/{id}")
    public void deleteProfileById(@PathVariable Long id) {
        deleteProfileUseCase.deleteProfileById(id);
    }
    @Operation(
            summary = "Actualizar un perfil existente por ID"
    )
    @PutMapping("/{id}")
    public Profile updateProfiles(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
        return updateProfileUseCase.updateProfiles( id, profileRequest);
    }
}
