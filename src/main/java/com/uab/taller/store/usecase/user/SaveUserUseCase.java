package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.Profile;
import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.CreateUserRequest;
import com.uab.taller.store.domain.dto.request.UserRequest;
import com.uab.taller.store.domain.dto.response.ProfileResponse;
import com.uab.taller.store.domain.dto.response.RolResponse;
import com.uab.taller.store.domain.dto.response.UserResponse;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.service.IRolService;
import com.uab.taller.store.service.IUserService;
import com.uab.taller.store.service.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SaveUserUseCase {
    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;
    @Autowired
    IRolService rolService;
    @Autowired
    UserServiceImp userServiceImp;
    public ResponseEntity<?> saveUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        // Validar email único
        if (userService.getUserByEmail(createUserRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya está registrado");
        }
        // Validar ci único
        if (profileService.findByCi(createUserRequest.getCi()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El CI ya está registrado");
        }
        // Validar status como enum
        if (!createUserRequest.getStatus().equalsIgnoreCase("ACTIVO") && !createUserRequest.getStatus().equalsIgnoreCase("INACTIVO")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El status debe ser ACTIVO o INACTIVO");
        }
        Profile profile = new Profile();
        profile.setName(createUserRequest.getName());
        profile.setLastName(createUserRequest.getLastName());
        profile.setAddress(createUserRequest.getAddress());
        profile.setCi(createUserRequest.getCi());
        profile.setAddUser("system");
        profile.setAddDate(java.time.LocalDateTime.now());
        profile.setMobile(createUserRequest.getMobile());
        profile.setStatus(createUserRequest.getStatus());
        profile.setDeleted(false);
        profile = profileService.saveProfile(profile);

        User user = new User();
        user.setPassword(createUserRequest.getPassword());
        user.setEmail(createUserRequest.getEmail());
        user.setProfile(profile);
        Rol rol = rolService.getById(createUserRequest.getRolId());
        user.setRol(rol);
        user.setAddUser("system");
        user.setAddDate(java.time.LocalDateTime.now());
        user.setDeleted(false);
        user = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(toUserResponse(user));
    }
    private UserResponse toUserResponse(User user) {
        if (user == null) return null;
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .profile(ProfileResponse.builder()
                        .id(user.getProfile() != null ? user.getProfile().getProfileId() : null)
                        .name(user.getProfile() != null ? user.getProfile().getName() : null)
                        .lastName(user.getProfile() != null ? user.getProfile().getLastName() : null)
                        .ci(user.getProfile() != null ? user.getProfile().getCi() : null)
                        .mobile(user.getProfile() != null ? user.getProfile().getMobile() : null)
                        .address(user.getProfile() != null ? user.getProfile().getAddress() : null)
                        .status(user.getProfile() != null ? user.getProfile().getStatus() : null)
                        .build())
                .rol(user.getRol() != null ? RolResponse.builder()
                        .id(user.getRol().getRolId())
                        .name(user.getRol().getName())
                        .description(user.getRol().getDescription())
                        .build() : null)
                .build();
    }
}
