package com.slippery.funfluent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chapters {
    private String message;
    private int statusCode;
    private java.util.List<Chapters> chapters;
    private Chapters chapter;
}
