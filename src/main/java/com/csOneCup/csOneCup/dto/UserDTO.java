package com.csOneCup.csOneCup.dto;

import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String name;
    private int level;
    private int expPoint;
    private List<DeckDTO> decks;
    private List<CardDTO> cards;
    private StatisticsDTO statistics;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .level(user.getLevel())
                .expPoint(user.getExpPoint())
                .decks(user.getDecks().stream()
                        .map(DeckDTO::fromEntity)
                        .collect(Collectors.toList()))
                .statistics(StatisticsDTO.fromEntity(user.getStatistics()))
                .cards(user.getCards().stream().map(Card::convertToCardDTO).collect(Collectors.toList()))
                .build();
    }
}
