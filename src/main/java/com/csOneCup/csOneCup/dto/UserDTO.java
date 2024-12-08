package com.csOneCup.csOneCup.dto;

import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.card.CardDTOConverter;
import com.csOneCup.csOneCup.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("user_id")
    private String userId;
    private String name;
    private int level;
    @JsonProperty("exp_point")
    private int expPoint;
    private List<DeckDTO> decks;
    private List<CardDTO> cards;
    private StatisticsDTO statistics;
}
