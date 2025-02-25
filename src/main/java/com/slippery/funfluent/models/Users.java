package com.slippery.funfluent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String language;
    private Long level =0L;
    private Long coins =0L;
    private String role;
    @OneToMany
    private List<StoryBook> savedBooks =new ArrayList<>();
    @OneToMany
    private List<StoryBook> readingList =new ArrayList<>();

}
