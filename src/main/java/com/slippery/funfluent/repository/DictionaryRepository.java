package com.slippery.funfluent.repository;

import com.slippery.funfluent.models.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Dictionary,Long> {
}
