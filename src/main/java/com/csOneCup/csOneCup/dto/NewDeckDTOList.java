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
public class NewDeckDTOList {
    private List<NewDeckDTO> decks;
}
