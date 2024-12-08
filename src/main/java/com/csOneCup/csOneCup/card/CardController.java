package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.deck_card_mapping.DeckCardMapping;
import com.csOneCup.csOneCup.dto.*;
import com.csOneCup.csOneCup.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Cards", description = "Cards API")
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

    @PostMapping("/user/add")
    public ResponseEntity<SuccessResponse<?>> addCardToUser(@RequestHeader("Authorization") String token, @RequestBody CardAddingRequest cardAddingRequest) {
        CardDTO card = cardService.addCardToUser(token, cardAddingRequest);
        return SuccessResponse.ok(card);
    }

    @PostMapping("/deck/add")
    public ResponseEntity<SuccessResponse<?>> addCardToDeck(@RequestHeader("Authorization") String token, @RequestBody CardAddingToDeckRequest cardAddingRequest) {
        CardDTO card = cardService.addCardToDeck(token, cardAddingRequest);
        return SuccessResponse.ok(card);
    }

    @GetMapping("/card")
    public ResponseEntity<SuccessResponse<?>> searchACard(@RequestHeader("Authorization") String token, @RequestParam boolean redundant, @RequestParam String category) {
        CardDTO cardDTO = cardService.getRandomCard(redundant, category, token);
        return SuccessResponse.ok(cardDTO);
    }
}
