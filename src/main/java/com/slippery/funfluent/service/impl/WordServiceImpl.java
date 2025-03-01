package com.slippery.funfluent.service.impl;

import com.slippery.funfluent.dto.WordDto;
import com.slippery.funfluent.models.Word;
import com.slippery.funfluent.repository.DictionaryRepository;
import com.slippery.funfluent.repository.WordRepository;
import com.slippery.funfluent.service.WordService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository repository;
    private final DictionaryRepository dictionaryRepository;

    public WordServiceImpl(WordRepository repository, DictionaryRepository dictionaryRepository) {
        this.repository = repository;
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public WordDto createNewWord(Word wordDetails, Long newWordsId) {
        WordDto response =new WordDto();
        var existingWordByName =repository.findByName(wordDetails.getName());
        var dictionary = dictionaryRepository.findById(newWordsId);

        if(dictionary.isEmpty()){
            response.setMessage("New word container is empty");
            response.setStatusCode(200);
            return response;
        }
        if(existingWordByName !=null){
            response.setMessage("Word already exists in collection");
            response.setStatusCode(200);
            return response;
        }
        wordDetails.setDictionary(dictionary.get());
        var wordList =dictionary.get().getWords();
        wordList.add(wordDetails);
        dictionary.get().setWords(wordList);
        repository.save(wordDetails);
        dictionaryRepository.save(dictionary.get());
        response.setMessage("New word added to the dictionary");
        response.setStatusCode(200);

        return response;
    }

    @Override
    public WordDto updateWord(Word updateDetails, Long wordId) {
        return null;
    }

    @Override
    public WordDto deleteWord(Long wordId) {
        WordDto response =new WordDto();
        var wordExistsInRepository =findWordById(wordId);
        if(wordExistsInRepository.getStatusCode() !=200){
            return wordExistsInRepository;
        }
        var existingWord =wordExistsInRepository.getWord();
        var dictionary =existingWord.getDictionary();
        var wordsInDictionary =dictionary.getWords();
        wordsInDictionary.remove(existingWord);
        dictionary.setWords(wordsInDictionary);
        dictionaryRepository.save(dictionary);
        repository.delete(existingWord);
        response.setMessage("Words deleted from dictionary ");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public WordDto findWordById(Long wordId) {
        WordDto response =new WordDto();
        Optional<Word> existingWord =repository.findById(wordId);
        if(existingWord.isEmpty()){
            response.setMessage("Word with id "+wordId+" does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Word with id "+wordId);
        response.setStatusCode(200);
        response.setWord(existingWord.get());
        return response;
    }
}
