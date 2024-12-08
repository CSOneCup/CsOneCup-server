package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.dto.CardDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class CsvDataLoader {

    private final CSVRepository csvRepository;

//    public CardDTO getCardData(int csvNumber) {
//        String filePath = "src/main/resources/dataset.csv";
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            br.readLine();
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] fields = line.split(",");
//                int currentCsvNumber = Integer.parseInt(fields[0]);
//                if (currentCsvNumber == csvNumber) {
//                    String quizType = fields[1];
//                    int choiceCount = quizType.equals("4지선다") ? 4 : 2; // 퀴즈 타입에 따라 선택지 개수 결정
//                    List<String> choices = List.of(fields).subList(5, 5 + choiceCount);
//
//                    return CardDTO.builder()
//                            .quizType(quizType)
//                            .title(fields[3])
//                            .category(fields[2])
//                            .question(fields[4])
//                            .choice(choices)
//                            .answer(Integer.parseInt(fields[9])) // 답은 선택지 이후 필드
//                            .explanation(fields[10]) // 해설은 답 이후 필드
//                            .csvNumber(currentCsvNumber)
//                            .build();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public CardDTO getCardData(Long csvNumber) {
        // 데이터베이스에서 csvNumber를 기준으로 데이터 조회
        CSVData csvData = csvRepository.findById(csvNumber)
                .orElseThrow(() -> new IllegalArgumentException("No data found for csvNumber: " + csvNumber));

        // CSVData에서 CardDTO로 매핑
        String quizType = csvData.getType();
        int choiceCount = quizType.equals("4지선다") ? 4 : 2; // 퀴즈 타입에 따라 선택지 개수 결정
        List<String> choices = List.of(
                csvData.getOption1(),
                csvData.getOption2(),
                csvData.getOption3(),
                csvData.getOption4()
        ).subList(0, choiceCount);

        return CardDTO.builder()
                .quizType(quizType)
                .title(csvData.getTitle())
                .category(csvData.getMainCategory())
                .question(csvData.getQuestion())
                .choice(choices)
                .answer(csvData.getAnswer().intValue()) // 답
                .explanation(csvData.getExplanation()) // 해설
                .csvNumber(csvData.getId().intValue())
                .build();
    }
}