package com.slippery.funfluent.controller;

import com.slippery.funfluent.dto.UserDto;
import com.slippery.funfluent.models.Users;
import com.slippery.funfluent.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
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
        return ResponseEntity.ok(service.login(userDetails));
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        return  ResponseEntity.ok(service.findUserById(id));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Long id) {
        return  ResponseEntity.ok(service.deleteUserById(id));
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<UserDto> deleteAll() {
        return  ResponseEntity.ok(service.deleteAll());
    }
    @GetMapping("/get/all")
    public ResponseEntity<UserDto> findAll() {
        return  ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{userId}/saved-books")
    public ResponseEntity<UserDto> findAllBooksSavedByUser(@PathVariable Long userId){
        return ResponseEntity.ok(service.findAllBooksSavedByUser(userId));
    }
    @PutMapping("/{userId}/update/country")
    public ResponseEntity<UserDto> setCountryDetails(@RequestBody Users userDetails,@PathVariable Long userId){
        return ResponseEntity.ok(service.setCountryDetails(userDetails, userId));
    }
}
