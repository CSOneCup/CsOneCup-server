package com.csOneCup.csOneCup.dto;
import com.csOneCup.csOneCup.deck.Deck;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class DeckDTO {
    @JsonProperty("deck_id")
    private Long deckId;
    private String name;

    @JsonProperty("number_of_cards")
    private int numberOfCards;
    private List<String> tags;

    public static DeckDTO fromEntity(Deck deck) {
        return DeckDTO.builder()
                .deckId(deck.getDeckId())
                .name(deck.getName())
                .numberOfCards(deck.getNumberOfCards())
                .tags(deck.getTags())
                .build();
    }
}
