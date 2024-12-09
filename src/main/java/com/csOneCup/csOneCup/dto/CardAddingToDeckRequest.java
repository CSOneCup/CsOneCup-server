package com.csOneCup.csOneCup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardAddingToDeckRequest {
    @JsonProperty("card_id")
    private Long cardId;

    @JsonProperty("deck_id")
    private Long deckId;
}
