package com.csOneCup.csOneCup.card;

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

//    public CardDTO convertToCardDTO(CSVRepository csvDataRepository) {
//        if (csvDataRepository == null) {
//            throw new IllegalStateException("CsvDataRepository가 설정되지 않았습니다.");
//        }
//
//        // DB에서 csvNumber로 CsvData 가져오기
//        CSVData csvData = csvDataRepository.findById((long)this.csvNumber).get();
//
//        if (csvData == null) {
//            throw new IllegalStateException("CSV 데이터가 존재하지 않습니다: " + this.csvNumber);
//        }
//
//        List<String> choices;
//        if ("OX".equalsIgnoreCase(csvData.getType())) {
//            choices = List.of(csvData.getOption1(), csvData.getOption2());
//        } else {
//            choices = List.of(
//                    csvData.getOption1(),
//                    csvData.getOption2(),
//                    csvData.getOption3(),
//                    csvData.getOption4()
//            );
//        }
//
//        return CardDTO.builder()
//                .cardId(this.cardId)
//                .quizType(csvData.getType())
//                .title(csvData.getTitle())
//                .category(csvData.getMainCategory())
//                .question(csvData.getQuestion())
//                .choice(choices)
//                .answer(csvData.getAnswer().intValue())
//                .explanation(csvData.getExplanation())
//                .ownerId(this.owner.getUserId())
//                .csvNumber(csvData.getId().intValue())
//                .build();
//    }
}
