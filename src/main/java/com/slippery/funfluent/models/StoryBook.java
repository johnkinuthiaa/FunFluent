package com.slippery.funfluent.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoryBook {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    @Lob
    private String synopsis;
    @Lob
    private byte[] imageCover;
    private String genre;
    private String language;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Chapters> chapters =new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Dictionary dictionary;
    private String author;
}
