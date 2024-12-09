package com.csOneCup.csOneCup.deck_card_mapping;

import com.csOneCup.csOneCup.deck.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckCardMappingRepository extends JpaRepository<DeckCardMapping, Long> {
    List<DeckCardMapping> findAllByDeck(Deck deck);
}
