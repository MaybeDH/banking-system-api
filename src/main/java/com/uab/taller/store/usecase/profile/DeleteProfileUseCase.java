package com.uab.taller.store.usecase.profile;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Service
public class DeleteProfileUseCase {
    @Autowired
    IProfileService profileService;
    public ResponseEntity<?> deleteProfileById(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        if (profile == null || profile.isDeleted()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil no encontrado o ya eliminado");
        }
        profile.setDeleted(true);
        profile.setChangeDate(LocalDateTime.now());
        profile.setChangeUser("system");
        profileService.saveProfile(profile);
        return ResponseEntity.ok("Perfil eliminado correctamente (soft delete)");
    }

}
