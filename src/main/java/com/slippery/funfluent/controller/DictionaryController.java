package com.slippery.funfluent.controller;

import com.slippery.funfluent.dto.DictionaryDto;
import com.slippery.funfluent.service.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dictionary")
@CrossOrigin
public class DictionaryController {
    private final DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }
    @GetMapping("/{dictionaryId}/get")
    public ResponseEntity<DictionaryDto> findDictionaryById(@PathVariable Long dictionaryId) {
        return ResponseEntity.ok(service.findDictionaryById(dictionaryId));
    }
    @GetMapping("/{dictionaryId}/")
    public ResponseEntity<DictionaryDto> findAllWordsInDictionary(@PathVariable Long dictionaryId) {
        return ResponseEntity.ok(service.findAllWordsInDictionary(dictionaryId));
    }
}
