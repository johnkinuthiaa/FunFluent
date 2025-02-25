package com.slippery.funfluent.service;

import com.slippery.funfluent.dto.UserDto;
import com.slippery.funfluent.models.Users;

public interface UsersService {
    UserDto registerUser(Users userDetails);
    UserDto login(Users userDetails);
    UserDto findUserById(Long id);
    UserDto deleteUserById(Long id);
    UserDto deleteAll();
    UserDto findAll();
}
