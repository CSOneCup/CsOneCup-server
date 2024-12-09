package com.csOneCup.csOneCup.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeckCreationRequestAndCard {
    private String name;
    @JsonProperty("card_id")
    private List<Long> cardId;
}
