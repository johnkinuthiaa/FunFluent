package com.slippery.funfluent.service.impl;

import com.slippery.funfluent.dto.UserDto;
import com.slippery.funfluent.models.Users;
import com.slippery.funfluent.repository.UserRepository;
import com.slippery.funfluent.service.UsersService;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UsersService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    public UserServiceImplementation(UserRepository repository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDto registerUser(Users userDetails) {
        UserDto response =new UserDto();
        Users existingUser =repository.findByUsername(userDetails.getUsername());
        if(existingUser !=null){
            response.setMessage("User with username "+userDetails.getUsername()+" already exists");
            response.setStatusCode(200);
            return response;
        }
        userDetails.setCoins(0L);
        userDetails.setRole("USER");
        userDetails.setCountryDetails(new ArrayList<>());
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userDetails.setLevel(0L);
        userDetails.setReadingList(new ArrayList<>());
        userDetails.setSavedBooks(new ArrayList<>());
        repository.save(userDetails);
        response.setMessage("User created successfully!");
        response.setStatusCode(201);
        response.setUser(userDetails);
        return response;
    }

    @Override
    public UserDto login(Users userDetails) {
        UserDto response =new UserDto();
        Users existingUser =repository.findByEmail(userDetails.getEmail());
        if(existingUser ==null){
            response.setMessage("User with username "+userDetails.getEmail()+" does exists");
            response.setStatusCode(404);
            return response;
        }

        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                existingUser.getUsername(),userDetails.getPassword()
        ));
        if (!authentication.isAuthenticated()) {
            response.setMessage("user not authenticated due to wrong credentials");
            response.setStatusCode(401);
            return response;
        }
        response.setMessage("User authentication completed!");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto findUserById(Long id) {
        UserDto response =new UserDto();
        Optional<Users> existingUser =repository.findById(id);
        if(existingUser.isEmpty()){
            response.setMessage("User with id "+id+" not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("user with id "+id);
        response.setStatusCode(200);
        response.setUser(existingUser.get());
        return response;
    }

    @Override
    public UserDto deleteUserById(Long id) {
        UserDto response =new UserDto();
        var findById =findUserById(id);
        if(findById.getStatusCode() ==404){
            return findById;
        }
        repository.delete(findById.getUser());
        response.setMessage(findById.getMessage()+ " deleted successfully!");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto deleteAll() {
        UserDto response =new UserDto();
        var allUsers =findAll();
        if(allUsers.getStatusCode() ==404){
            return allUsers;
        }
        repository.deleteAll();
        response.setMessage("All users deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto findAll() {
        UserDto response =new UserDto();
        var allUsers =repository.findAll();
        if(allUsers.isEmpty()){
            response.setUsers(new ArrayList<>());
            response.setStatusCode(404);
            response.setMessage("User list is empty");
            return response;
        }
        response.setUsers(allUsers);
        response.setStatusCode(200);
        response.setMessage("User list");
        return response;
    }

    @Override
    public UserDto findAllBooksSavedByUser(Long userId) {
        UserDto response =new UserDto();
        var existingUser =findUserById(userId);
        if(existingUser.getStatusCode() !=200){
            return existingUser;
        }
        var books =existingUser.getUser().getSavedBooks();
        response.setSavedBooks(books);
        response.setStatusCode(200);
        response.setMessage("All books saved by user");

        return response;
    }

    @Override
    public UserDto saveBook(Long userId, Long bookId) {
        return null;
    }

    @Override
    public UserDto setCountryDetails(Users userDetails, Long userId) {
        var existingUser =findUserById(userId);
        UserDto response =new UserDto();
        if(existingUser.getStatusCode() !=200){
            return response;
        }
        var user =existingUser.getUser();
        user.setCountryDetails(userDetails.getCountryDetails());
        repository.save(user);
        response.setUser(user);
        response.setMessage("Country info updated");
        response.setStatusCode(200);
        return response;
    }
}
