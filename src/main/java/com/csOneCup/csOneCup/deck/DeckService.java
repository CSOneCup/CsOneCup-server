package com.csOneCup.csOneCup.deck;

import com.csOneCup.csOneCup.auth.JWTUtil;
import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.card.CardRepository;
import com.csOneCup.csOneCup.deck.Deck;
import com.csOneCup.csOneCup.deck.DeckRepository;
import com.csOneCup.csOneCup.dto.CardsInDeck;
import com.csOneCup.csOneCup.dto.DeckCreationRequest;
import com.csOneCup.csOneCup.dto.DeckResponse;
import com.csOneCup.csOneCup.global.error.exception.UnauthorizedException;
import com.csOneCup.csOneCup.user.User;
import com.csOneCup.csOneCup.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeckService {
    private final DeckRepository deckRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public CardsInDeck SearchCardsInDeck(Long deckId, String token) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found"));

        if(!deck.getOwner().getUserId().equals(JWTUtil.extractUserId(token))) throw new UnauthorizedException();

        return CardsInDeck.builder()
                .deck_id(deckId)
                .cards(deck.getCards().stream().map(card -> card.convertToCardDTO()).collect(Collectors.toList()))
                .build();
    }

    public DeckResponse createDeck(String token, DeckCreationRequest request) {
        // 사용자 검증
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 덱 생성
        Deck deck = Deck.builder()
                .name(request.getName())
                .owner(user)
                .build();
        deckRepository.save(deck);

        // 카드 추가
        List<Card> cards = request.getCards().stream()
                .map(cardDTO -> Card.builder()
                        .csvNumber(cardDTO.getCsvNumber()) // CSV 넘버만 저장
                        .deck(deck) // 해당 카드의 소속 덱
                        .owner(user) // 카드 소유자
                        .build())
                .collect(Collectors.toList());
        cardRepository.saveAll(cards);

        return DeckResponse.builder()
                .deckId(deck.getDeckId())
                .build();
    }
}
