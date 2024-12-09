package com.csOneCup.csOneCup.user;

import com.csOneCup.csOneCup.card.Card;
import com.csOneCup.csOneCup.deck.Deck;
import com.csOneCup.csOneCup.statistics.Statistics;
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
@Table(name = "user")
public class User {
    @Id
    @Column(length = 30, nullable = false, unique = true)
    private String userId;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int expPoint;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deck> decks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

    public void earnEXP() {
        this.expPoint+=60;
        this.level+=this.expPoint/100;
        this.expPoint%=100;
    }
}
