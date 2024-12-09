package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.auth.JWTUtil;
import com.csOneCup.csOneCup.deck.Deck;
import com.csOneCup.csOneCup.deck.DeckRepository;
import com.csOneCup.csOneCup.deck_card_mapping.DeckCardMapping;
import com.csOneCup.csOneCup.deck_card_mapping.DeckCardMappingRepository;
import com.csOneCup.csOneCup.dto.*;
import com.csOneCup.csOneCup.user.User;
import com.csOneCup.csOneCup.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;
    private final DeckCardMappingRepository deckCardMappingRepository;
    private final CsvDataLoader csvDataLoader;
    private final CardDTOConverter cardDTOConverter;


    public List<CardDTO> searchUserCards(String token, String category, String query) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getCards().stream().map(card -> cardDTOConverter.convertToCardDTO(card))
                .filter(card -> category == null || card.getCategory().equalsIgnoreCase(category))
                .filter(card -> query == null ||
                        card.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        card.getQuestion().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public CardDTO addCardToUser(String token, CardAddingRequest cardAddingRequest) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Card card = cardRepository.save(
                Card.builder()
                        .owner(user)
                        .csvNumber(cardAddingRequest.getCsvNum())
                        .build()
        );

        user.earnEXP();
        return cardDTOConverter.convertToCardDTO(card);
    }

    public CardDTO addCardToDeck(String token, CardAddingToDeckRequest cardAddingRequest) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Card> card = cardRepository.findById(cardAddingRequest.getCardId());
        Optional<Deck> deck = deckRepository.findById(cardAddingRequest.getDeckId());

        if(card.isEmpty() || deck.isEmpty()) throw new RuntimeException();
        if(!card.get().getOwner().equals(user) || !deck.get().getOwner().equals(user)) throw new RuntimeException("권한이 없습니다");

        Card realCard = card.get();
        Deck realDeck = deck.get();

        deckCardMappingRepository.save(
                DeckCardMapping.builder()
                        .deck(realDeck)
                        .card(realCard)
                        .build()
        );
        deck.get().increaseCardNum();
        deckRepository.save(deck.get());

        return cardDTOConverter.convertToCardDTO(realCard);
    }

    public boolean createDeckAndAddCard(String token, DeckCreationRequestAndCard request) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Deck deck = Deck.builder()
                .name(request.getName())
                .owner(user)
                .build();
        deckRepository.save(deck);
        List<Card> card = cardRepository.findByCardIdIn(request.getCardId());

        System.out.println(card.size());

        for (Card value : card) {
            deckCardMappingRepository.save(
                    DeckCardMapping.builder()
                            .deck(deck)
                            .card(value)
                            .build()
            );
        }
        return true;
    }

    public CardDTO getRandomCard(boolean redundant, String category, String token) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Random random = new Random();
        List<Integer> ownedCsvNumbers = cardRepository.findAllByOwner(user).stream().mapToInt(Card::getCsvNumber).boxed().toList();

        while (true) {
            int randomCsvNumber = random.nextInt(252) + 1;

            if (!redundant && ownedCsvNumbers.contains(randomCsvNumber)) {
                continue;
            }

            CardDTO card = csvDataLoader.getCardData((long)randomCsvNumber);

            if (card != null && (card.getCategory().equals(category) || category.equals("all"))) {
                return card;
            }
        }
    }
}
