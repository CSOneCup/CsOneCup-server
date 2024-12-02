package com.csOneCup.csOneCup.deck_card_mapping;

import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.deck.Deck;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deck_card_mapping", uniqueConstraints = {@UniqueConstraint(columnNames = {"deck_id", "card_id"})})
public class DeckCardMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}

