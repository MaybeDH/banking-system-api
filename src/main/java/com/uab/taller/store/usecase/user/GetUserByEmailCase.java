package com.uab.taller.store.usecase.user;

import com.uab.taller.store.domain.User;
import com.uab.taller.store.domain.dto.request.GetUserByEmailRequest;
import com.uab.taller.store.service.IProfileService;
import com.uab.taller.store.service.IRolService;
import com.uab.taller.store.service.IUserService;
import com.uab.taller.store.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserByEmailCase {
    @Autowired
    IUserService userService;
    @Autowired
    IProfileService profileService;
    @Autowired
    IRolService rolService;
    @Autowired
    UserServiceImp userServiceImp;
    public User getUserByEmail(String email ) {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }else{
            return null;
        }
    }
}
