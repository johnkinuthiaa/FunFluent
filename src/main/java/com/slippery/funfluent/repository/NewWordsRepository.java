package com.slippery.funfluent.repository;

import com.slippery.funfluent.models.NewWords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewWordsRepository extends JpaRepository<NewWords,Long> {
}
