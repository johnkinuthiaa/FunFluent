package com.slippery.funfluent.service.impl;

import com.slippery.funfluent.dto.StoryBookDto;
import com.slippery.funfluent.models.Chapters;
import com.slippery.funfluent.models.StoryBook;
import com.slippery.funfluent.repository.ChaptersRepository;
import com.slippery.funfluent.repository.StoryBookRepository;
import com.slippery.funfluent.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StorybookServiceImplementation implements BookService {
    private final StoryBookRepository repository;
    private final ChaptersRepository chaptersRepository;

    public StorybookServiceImplementation(StoryBookRepository repository, ChaptersRepository chaptersRepository) {
        this.repository = repository;
        this.chaptersRepository = chaptersRepository;
    }

    @Override
    public StoryBookDto createNewStoryBook(StoryBook storyBook) {
        StoryBookDto response =new StoryBookDto();
        storyBook.setChapters(new ArrayList<>());;
        response.setMessage("New storybook added");
        response.setStatusCode(200);
        response.setStoryBook(storyBook);
        return response;
    }

    @Override
    public StoryBookDto updateStoryBook(StoryBook storyBookDetails, Long id) {
        return null;
    }

    @Override
    public StoryBookDto findStoryBookById(Long id) {
        StoryBookDto response =new StoryBookDto();
        Optional<StoryBook> existingStoryBook =repository.findById(id);
        if(existingStoryBook.isEmpty()){
            response.setMessage("book does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setStatusCode(200);
        response.setMessage("Book with id"+id);
        response.setStoryBook(existingStoryBook.get());
        return response;
    }

    @Override
    public StoryBookDto findStoryBooksByGenre(String genre) {
        StoryBookDto response =new StoryBookDto();
        var existingGenre =repository.findAll().stream()
                .filter(storyBook -> storyBook.getGenre().equalsIgnoreCase(genre))
                .toList();
        if(existingGenre.isEmpty()){
            response.setMessage("Stories of "+genre+" genre not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Stories of "+genre+" genre found");
        response.setStatusCode(200);
        response.setStoryBookList(existingGenre);
        return response;
    }

    @Override
    public StoryBookDto findStoryBooksByLanguage(String language) {
        StoryBookDto response =new StoryBookDto();
        var existingByLanguage = repository.findAll().stream()
                .filter(storyBook -> storyBook.getLanguage().equalsIgnoreCase(language))
                .toList();

        if(existingByLanguage.isEmpty()){
            response.setMessage("Stories of "+language+" language not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Stories of "+language+" language found");
        response.setStatusCode(200);
        response.setStoryBookList(existingByLanguage);
        return response;

    }

    @Override
    public StoryBookDto findStoryBooksByName(String title) {
        StoryBookDto response =new StoryBookDto();
        var storyTitle = repository.findAll().stream()
                .filter(storyBook -> storyBook.getLanguage().equalsIgnoreCase(title))
                .toList();

        if(storyTitle.isEmpty()){
            response.setMessage("Stories with the "+title+" not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Stories with the title "+title+" language found");
        response.setStatusCode(200);
        response.setStoryBookList(storyTitle);
        return response;
    }

    @Override
    public StoryBookDto addChapter(Chapters chapter,Long bookId) {
        StoryBookDto response =new StoryBookDto();
        var existingStoryBook =findStoryBookById(bookId);
        if(existingStoryBook.getStatusCode() ==404){
            return response;
        }
        var book =existingStoryBook.getStoryBook();
        chapter.setStoryBook(existingStoryBook.getStoryBook());
        var chapters =book.getChapters();
        chapters.add(chapter);
        book.setChapters(chapters);
        repository.save(book);
        chaptersRepository.save(chapter);
        response.setMessage("New chapter added to storybook");
        response.setStoryBook(book);
        response.setStatusCode(200);
        return response;
    }
}
