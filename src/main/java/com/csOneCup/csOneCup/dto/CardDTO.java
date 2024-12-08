package com.csOneCup.csOneCup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CardDTO {
    @JsonProperty("card_id")
    private Long cardId;

    @JsonProperty("quiz_type")
    private String quizType;

    private String title;
    private String category;
    private String question;
    private List<String> choice;
    private int answer;
    private String explanation;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("deck_id")
    private Long deckId;

    @JsonProperty("csv_number")
    private int csvNumber;
}
