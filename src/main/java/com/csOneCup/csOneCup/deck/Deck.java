package com.csOneCup.csOneCup.deck;

import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deck")
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deckId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int numberOfCards;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    public void increaseCardNum() {
        numberOfCards++;
    }
}
