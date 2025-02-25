package com.slippery.funfluent.service;

import com.slippery.funfluent.dto.StoryBookDto;
import com.slippery.funfluent.models.Chapters;
import com.slippery.funfluent.models.StoryBook;

import java.util.List;

public interface BookService {
    StoryBookDto createNewStoryBook(StoryBook storyBook);
    StoryBookDto updateStoryBook(StoryBook storyBookDetails,Long id);
    StoryBookDto findStoryBookById(Long id);
    StoryBookDto findStoryBooksByGenre(String genre);
    StoryBookDto findStoryBooksByLanguage(String language);
    StoryBookDto findStoryBooksByName(String title);
    StoryBookDto addChapter(Chapters chapter,Long bookId);

}
