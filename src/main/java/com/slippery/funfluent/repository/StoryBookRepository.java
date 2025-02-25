package com.slippery.funfluent.repository;

import com.slippery.funfluent.models.StoryBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryBookRepository extends JpaRepository<StoryBook,Long> {
}
