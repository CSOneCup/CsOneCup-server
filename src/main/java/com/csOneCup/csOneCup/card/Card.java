package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.deck.Deck;
import com.csOneCup.csOneCup.dto.CardDTO;
import com.csOneCup.csOneCup.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @Column(nullable = false)
    private String quizType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String question;

    @ElementCollection
    private List<String> choice;

    @Column(nullable = false)
    private int answer;

    @Column(nullable = false)
    private String explanation;

    public CardDTO convertToCardDTO() {
        return CardDTO.builder()
                .cardId(this.getCardId())
                .quizType(this.getQuizType())
                .title(this.getTitle())
                .category(this.getCategory())
                .question(this.getQuestion())
                .choice(this.getChoice())
                .answer(this.getAnswer())
                .explanation(this.getExplanation())
                .ownerId(this.getOwner().getUserId())
                .deckId(this.getDeck() != null ? this.getDeck().getDeckId() : null)
                .build();
    }
}
