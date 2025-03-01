package com.slippery.funfluent.repository;

import com.slippery.funfluent.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Long> {
    Word findByName(String name);
}
