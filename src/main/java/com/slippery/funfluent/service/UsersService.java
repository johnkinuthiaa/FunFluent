package com.slippery.funfluent.service;

import com.slippery.funfluent.dto.UserDto;
import com.slippery.funfluent.models.Users;

import java.util.List;

public interface UsersService {
    UserDto registerUser(Users userDetails);
    UserDto login(Users userDetails);
    UserDto findUserById(Long id);
    UserDto deleteUserById(Long id);
    UserDto deleteAll();
    UserDto findAll();
    UserDto findAllBooksSavedByUser(Long userId);
    UserDto saveBook(Long userId,Long bookId);
    UserDto setCountryDetails(Users userDetails, Long userId);
}
