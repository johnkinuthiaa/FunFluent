package com.slippery.funfluent.service;

import com.slippery.funfluent.models.UserPrincipal;
import com.slippery.funfluent.models.Users;
import com.slippery.funfluent.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public MyUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users existingUser =repository.findByUsername(username);
        if(existingUser ==null){
            log.warn("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(existingUser);
    }
}
