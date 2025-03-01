package com.slippery.funfluent.service;

import com.slippery.funfluent.dto.DictionaryDto;

public interface DictionaryService {
    DictionaryDto findDictionaryById(Long dictionaryId);
    DictionaryDto findAllWordsInDictionary(Long dictionaryId);
}
