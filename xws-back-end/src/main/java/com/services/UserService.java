package com.services;

import com.model.User;
import com.model.dto.RegistrationUserDto;
import com.model.dto.LoginUserDto;

public interface UserService {
    boolean registerUser(RegistrationUserDto rUDTO);
    void setCurrentUser(User user);
    User findUser(LoginUserDto lUDTO);
    User getCurrentUser();
}