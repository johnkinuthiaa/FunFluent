package com.slippery.funfluent.controller;

import com.slippery.funfluent.dto.WordDto;
import com.slippery.funfluent.models.Word;
import com.slippery.funfluent.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/words")
@CrossOrigin
public class WordController {
    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }
    @PostMapping("/create/new")
    public ResponseEntity<WordDto> createNewWord(@RequestBody Word wordDetails, @RequestParam Long dictionaryId) {
        return ResponseEntity.ok(service.createNewWord(wordDetails, dictionaryId));
    }
    @PutMapping("/update")
    public ResponseEntity<WordDto>  updateWord(@RequestBody Word wordDetails, @RequestParam Long wordId) {
        return ResponseEntity.ok(service.updateWord(wordDetails, wordId));
    }
    @DeleteMapping("/{wordId}/delete")
    public ResponseEntity<WordDto>  deleteWord(@PathVariable Long wordId) {
        return ResponseEntity.ok(service.deleteWord(wordId));
    }
    @GetMapping("/{wordId}/get")
    public ResponseEntity<WordDto>  findWordById(@PathVariable Long wordId) {
        return ResponseEntity.ok(service.findWordById(wordId));
    }
}
