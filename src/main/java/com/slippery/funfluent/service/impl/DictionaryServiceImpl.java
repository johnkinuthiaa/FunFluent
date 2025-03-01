package com.slippery.funfluent.service.impl;

import com.slippery.funfluent.dto.DictionaryDto;
import com.slippery.funfluent.models.Dictionary;
import com.slippery.funfluent.repository.DictionaryRepository;
import com.slippery.funfluent.service.DictionaryService;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository repository;

    public DictionaryServiceImpl(DictionaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public DictionaryDto findDictionaryById(Long dictionaryId) {
        DictionaryDto response =new DictionaryDto();
        var existingDictionary =repository.findById(dictionaryId);
        if(existingDictionary.isEmpty()){
            response.setMessage("Dictionary is empty");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Dictionary with id "+dictionaryId);
        response.setDictionary(existingDictionary.get());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public DictionaryDto findAllWordsInDictionary(Long dictionaryId) {
        DictionaryDto response =new DictionaryDto();
        var existingDictionary =findDictionaryById(dictionaryId);
        if(existingDictionary.getStatusCode() !=200){
            return existingDictionary;
        }
        Dictionary dictionary =existingDictionary.getDictionary();
        var words =dictionary.getWords();
        response.setMessage("All words in dictionary");
        response.setStatusCode(200);
        response.setWordList(words);
        return response;
    }
}
