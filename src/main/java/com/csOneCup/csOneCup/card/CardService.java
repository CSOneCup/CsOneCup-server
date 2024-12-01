package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.auth.JWTUtil;
import com.csOneCup.csOneCup.dto.CardDTO;
import com.csOneCup.csOneCup.user.User;
import com.csOneCup.csOneCup.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    private final UserRepository userRepository;

    public CardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<CardDTO> searchUserCards(String token, String category, String query) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getCards().stream()
                .filter(card -> category == null || card.getCategory().equalsIgnoreCase(category))
                .filter(card -> query == null ||
                        card.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        card.getQuestion().toLowerCase().contains(query.toLowerCase()))
                .map(card -> card.convertToCardDTO())
                .collect(Collectors.toList());
    }
}
