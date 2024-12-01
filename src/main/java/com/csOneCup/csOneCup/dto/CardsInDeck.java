package com.csOneCup.csOneCup.dto;

import com.csOneCup.csOneCup.card.Card;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class CardsInDeck {
    @JsonProperty("deck_id")
    Long deck_id;

    List<CardDTO> cards;
}
