package com.csOneCup.csOneCup.dto;

import com.csOneCup.csOneCup.statistics.Statistics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StatisticsDTO {
    private Long id;
    private int correctRate;
    private int dailySolved;
    private int monthlySolved;
    private int totalSolved;
    private int maxStreak;
    private int collectedCards;

    public static StatisticsDTO fromEntity(Statistics statistics) {
        if(statistics == null) return null;

        return StatisticsDTO.builder()
                .id(statistics.getId())
                .correctRate(statistics.getCorrectRate())
                .dailySolved(statistics.getDailySolved())
                .monthlySolved(statistics.getMonthlySolved())
                .totalSolved(statistics.getTotalSolved())
                .maxStreak(statistics.getMaxStreak())
                .collectedCards(statistics.getCollectedCards())
                .build();
    }
}
