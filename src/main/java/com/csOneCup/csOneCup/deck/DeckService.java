package com.csOneCup.csOneCup.deck;

import com.csOneCup.csOneCup.auth.JWTUtil;
import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.card.CardDTOConverter;
import com.csOneCup.csOneCup.card.CardRepository;
import com.csOneCup.csOneCup.deck.Deck;
import com.csOneCup.csOneCup.deck.DeckRepository;
import com.csOneCup.csOneCup.deck_card_mapping.DeckCardMapping;
import com.csOneCup.csOneCup.deck_card_mapping.DeckCardMappingRepository;
import com.csOneCup.csOneCup.dto.*;
import com.csOneCup.csOneCup.global.error.exception.UnauthorizedException;
import com.csOneCup.csOneCup.user.User;
import com.csOneCup.csOneCup.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeckService {
    private final DeckRepository deckRepository;
    private final DeckCardMappingRepository deckCardMappingRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardDTOConverter cardDTOConverter;

    public CardsInDeck SearchCardsInDeck(Long deckId, String token) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found"));

        List<DeckCardMapping> deckCardMappings = deckCardMappingRepository.findAllByDeck(deck);

        return CardsInDeck.builder()
                .deckInfo(DeckDTO.fromEntity(deck))
                .cards(deckCardMappings.stream().map(deckCardMapping -> cardDTOConverter.convertToCardDTO(deckCardMapping.getCard())).collect(Collectors.toList()))
                .build();
    }

    public DeckResponse createDeck(String token, DeckCreationRequest request) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Deck deck = Deck.builder()
                .name(request.getName())
                .owner(user)
                .build();
        deckRepository.save(deck);

        return DeckResponse.builder()
                .deckId(deck.getDeckId())
                .build();
    }
    public NewDeckDTOList getThreeDeck(String token) {
        List<Deck> allDecks = deckRepository.findAll();

        // 3개 이하인 경우
        if (allDecks.size() <= 3) {
            List<NewDeckDTO> dtoList = allDecks.stream()
                    .map(deck -> NewDeckDTO.builder()
                            .deckId(deck.getDeckId())
                            .name(deck.getName())
                            .owner(UserDTO.builder()
                                    .userId(deck.getOwner().getUserId())
                                    .name(deck.getOwner().getName())
                                    .level(deck.getOwner().getLevel())
                                    .expPoint(deck.getOwner().getExpPoint())
                                    .build())
                            .build())
                    .collect(Collectors.toList());

            return NewDeckDTOList.builder()
                    .decks(dtoList)
                    .build();
        }

        // 3개 초과인 경우
        Collections.shuffle(allDecks);
        List<NewDeckDTO> dtoList = allDecks.stream()
                .limit(3) // 3개만 선택
                .map(deck -> NewDeckDTO.builder()
                        .deckId(deck.getDeckId())
                        .name(deck.getName())
                        .owner(UserDTO.builder()
                                .userId(deck.getOwner().getUserId())
                                .name(deck.getOwner().getName())
                                .level(deck.getOwner().getLevel())
                                .expPoint(deck.getOwner().getExpPoint())
                                .build())
                        .build())
                .collect(Collectors.toList());

        return NewDeckDTOList.builder()
                .decks(dtoList)
                .build();
    }
}
