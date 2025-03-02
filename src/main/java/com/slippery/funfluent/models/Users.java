package com.slippery.funfluent.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Lob
    private List<String> countryDetails;
    private Long level ;
    private Long coins ;
    private String role;
    @OneToMany
    private List<StoryBook> savedBooks;
    @OneToMany
    private List<StoryBook> readingList;

}
