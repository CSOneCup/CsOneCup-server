package com.csOneCup.csOneCup.deck;

import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.deck.DeckService;
import com.csOneCup.csOneCup.dto.*;
import com.csOneCup.csOneCup.global.common.SuccessCode;
import com.csOneCup.csOneCup.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
@RequiredArgsConstructor
@Tag(name = "Decks", description = "Decks API")
public class DeckController {

    private final DeckService deckService;

    @GetMapping("/{deckId}/cards")
    public ResponseEntity<SuccessResponse<?>> SearchCardsInDeck(@PathVariable Long deckId, @RequestHeader("Authorization") String token) {
        CardsInDeck cards = deckService.SearchCardsInDeck(deckId, token);
        return SuccessResponse.ok(cards);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createDeck(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody DeckCreationRequest request) {
        DeckResponse deckResponse = deckService.createDeck(authorizationHeader, request);

        return SuccessResponse.ok(deckResponse);
    }

    @GetMapping("/random3")
    public ResponseEntity<SuccessResponse<?>> getThreeDeck(@RequestHeader("Authorization") String token) {
        NewDeckDTOList deck = deckService.getThreeDeck(token);
        return SuccessResponse.ok(deck);
    }
}
