package com.slippery.funfluent.controller;

import com.slippery.funfluent.dto.UserDto;
import com.slippery.funfluent.models.Users;
import com.slippery.funfluent.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UsersService service;

    public UserController(UsersService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody Users userDetails) {
        return ResponseEntity.ok(service.registerUser(userDetails));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Users userDetails) {
        return null;
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        return null;
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Long id) {
        return null;
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<UserDto> deleteAll() {
        return null;
    }
    @GetMapping("/get/all")
    public ResponseEntity<UserDto> findAll() {
        return null;
    }
}
