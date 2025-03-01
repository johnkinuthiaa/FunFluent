package com.slippery.funfluent.service;

import com.slippery.funfluent.dto.WordDto;
import com.slippery.funfluent.models.Word;

public interface WordService {
    WordDto createNewWord(Word wordDetails,Long newWordsId);
    WordDto updateWord(Word updateDetails,Long wordId);
    WordDto deleteWord(Long wordId);
    WordDto findWordById(Long wordId);
}
