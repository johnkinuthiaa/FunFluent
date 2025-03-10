package com.slippery.funfluent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.funfluent.models.Word;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WordDto {
    private String message;
    private int statusCode;
    private Word word;
    private List<Word> wordList;
}
