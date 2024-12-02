package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByOwner(User user);
}
