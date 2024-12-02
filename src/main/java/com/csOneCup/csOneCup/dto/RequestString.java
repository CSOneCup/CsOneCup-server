package com.csOneCup.csOneCup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RequestString {
    @JsonProperty("response")
    private String request;
}
