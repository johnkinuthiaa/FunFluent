package com.slippery.funfluent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.funfluent.models.StoryBook;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoryBookDto {
    private String message;
    private int statusCode;
    private StoryBook storyBook;
    private List<StoryBook> storyBookList;
}
