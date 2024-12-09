package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.dto.CardDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardDTOConverter {

    private final CSVRepository csvDataRepository;

    public CardDTOConverter(CSVRepository csvDataRepository) {
        this.csvDataRepository = csvDataRepository;
    }

    public CardDTO convertToCardDTO(Card card) {
        // DB에서 csvNumber로 CsvData 가져오기
        CSVData csvData = csvDataRepository.findById((long) card.getCsvNumber())
                .orElseThrow(() -> new IllegalStateException("CSV 데이터가 존재하지 않습니다: " + card.getCsvNumber()));

        // 선택지 생성
        List<String> choices;
        if ("OX".equalsIgnoreCase(csvData.getType())) {
            choices = List.of(csvData.getOption1(), csvData.getOption2());
        } else {
            choices = List.of(
                    csvData.getOption1(),
                    csvData.getOption2(),
                    csvData.getOption3(),
                    csvData.getOption4()
            );
        }

        // CardDTO 생성 및 반환
        return CardDTO.builder()
                .cardId(card.getCardId())
                .quizType(csvData.getType())
                .title(csvData.getTitle())
                .category(csvData.getMainCategory())
                .question(csvData.getQuestion())
                .choice(choices)
                .answer(csvData.getAnswer().intValue())
                .explanation(csvData.getExplanation())
                .ownerId(card.getOwner().getUserId())
                .csvNumber(csvData.getId().intValue())
                .build();
    }
}
