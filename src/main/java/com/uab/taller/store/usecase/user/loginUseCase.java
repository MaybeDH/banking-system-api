package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.LoginRequest;
import com.uab.taller.store.domain.dto.response.ProfileResponse;
import com.uab.taller.store.domain.dto.response.RolResponse;
import com.uab.taller.store.domain.dto.response.UserResponse;
import com.uab.taller.store.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class loginUseCase {

    @Autowired
    UserServiceImp userServiceImp;
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userServiceImp.login(loginRequest.getEmail(), loginRequest.getPassword());
        return user
                .map(u -> ResponseEntity.ok(toUserResponse(u)))
                .orElse(ResponseEntity.notFound().build());
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
