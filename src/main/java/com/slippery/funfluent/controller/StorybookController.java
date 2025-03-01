package com.slippery.funfluent.controller;

import com.slippery.funfluent.dto.StoryBookDto;
import com.slippery.funfluent.models.Chapters;
import com.slippery.funfluent.models.StoryBook;
import com.slippery.funfluent.service.BookService;
import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/storybook")
@CrossOrigin
public class StorybookController{
    private final BookService service;

    public StorybookController(BookService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<StoryBookDto> createNewStoryBook(@RequestBody StoryBook storyBook) {
        return ResponseEntity.ok(service.createNewStoryBook(storyBook));
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<StoryBookDto> updateStoryBook(@RequestBody StoryBook storyBookDetails,@PathVariable Long id) {
        return ResponseEntity.ok(service.updateStoryBook(storyBookDetails, id));
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<StoryBookDto> findStoryBookById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findStoryBookById(id));
    }
    @GetMapping("/get/genre")
    public ResponseEntity<StoryBookDto> findStoryBooksByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(service.findStoryBooksByGenre(genre));
    }
    @GetMapping("/get/language")
    public ResponseEntity<StoryBookDto> findStoryBooksByLanguage(@RequestParam String language) {
        return ResponseEntity.ok(service.findStoryBooksByLanguage(language));
    }
    @GetMapping("/get/title")
    public ResponseEntity<StoryBookDto> findStoryBooksByName(@RequestParam String title) {
        return ResponseEntity.ok(service.findStoryBooksByName(title));
    }
    @PutMapping("/{bookId}/add-chapter")
    public ResponseEntity<StoryBookDto> addChapter(@RequestBody Chapters chapter,@PathVariable Long bookId) {
        return ResponseEntity.ok(service.addChapter(chapter, bookId));
    }
    @GetMapping("/get/all")
    public ResponseEntity<StoryBookDto> getAllBooks(){
        return ResponseEntity.ok(service.getAllBooks());
    }
}
