package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.User;
import com.uab.taller.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class DeleteUserUseCase {
    @Autowired
    IUserService userService;

    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null || user.isDeleted()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado o ya eliminado");
        }
        user.setDeleted(true);
        user.setChangeDate(java.time.LocalDateTime.now());
        user.setChangeUser("system");
        userService.saveUser(user);
        return ResponseEntity.ok("Usuario eliminado correctamente (soft delete)");
    }
}
