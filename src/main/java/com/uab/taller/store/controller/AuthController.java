package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Account;
import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.LoginRequest;
import com.uab.taller.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserServiceImp userServiceImp;


//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
//        Optional<User> user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
//        return user.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userServiceImp.login(loginRequest.getEmail(), loginRequest.getPassword());
        return user
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
