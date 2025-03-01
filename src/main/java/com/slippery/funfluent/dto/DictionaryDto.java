package com.slippery.funfluent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.funfluent.models.Dictionary;
import com.slippery.funfluent.models.Word;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DictionaryDto {
    private String message;
    private Integer statusCode;
    private Dictionary dictionary;
    private List<Dictionary> dictionaryList;
    private List<Word> wordList;
}
