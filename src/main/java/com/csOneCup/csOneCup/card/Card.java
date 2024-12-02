package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.deck.Deck;
import com.csOneCup.csOneCup.dto.CardDTO;
import com.csOneCup.csOneCup.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card", uniqueConstraints = {@UniqueConstraint(columnNames = {"owner_id", "csvNumber"})})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private int csvNumber;

    public CardDTO convertToCardDTO() {
        // CsvDataLoader 객체를 직접 생성
        CsvDataLoader csvDataLoader = new CsvDataLoader();
        CardDTO csvData = csvDataLoader.getCardData(this.csvNumber);

        if (csvData == null) {
            throw new IllegalStateException("CSV 데이터가 존재하지 않습니다: " + this.csvNumber);
        }

        return CardDTO.builder()
                .cardId(this.cardId)
                .quizType(csvData.getQuizType())
                .title(csvData.getTitle())
                .category(csvData.getCategory())
                .question(csvData.getQuestion())
                .choice(csvData.getChoice())
                .answer(csvData.getAnswer())
                .explanation(csvData.getExplanation())
                .ownerId(this.owner.getUserId())
                .csvNumber(csvData.getCsvNumber())
                .build();
    }
}
