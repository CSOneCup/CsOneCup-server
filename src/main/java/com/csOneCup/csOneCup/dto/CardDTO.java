package com.csOneCup.csOneCup.dto;

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
    private Long cardId;
    private String quizType;
    private String title;
    private String category;
    private String question;
    private List<String> choice;
    private int answer;
    private String explanation;
    private String ownerId;
    private Long deckId;
    private int csvNumber;
}
