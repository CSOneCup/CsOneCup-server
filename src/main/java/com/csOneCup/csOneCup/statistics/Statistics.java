package com.csOneCup.csOneCup.statistics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int correctRate;
    private int dailySolved;
    private int monthlySolved;
    private int totalSolved;
    private int maxStreak;
    private int collectedCards;
}
