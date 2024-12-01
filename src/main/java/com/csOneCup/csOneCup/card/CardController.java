package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.dto.CardDTO;
import com.csOneCup.csOneCup.global.common.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/user")
    public ResponseEntity<SuccessResponse<?>> searchUserCards(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String query) {
        List<CardDTO> cards = cardService.searchUserCards(token, category, query);
        return SuccessResponse.ok(cards);
    }
}
