package com.csOneCup.csOneCup.card;

import com.csOneCup.csOneCup.auth.JWTUtil;
import com.csOneCup.csOneCup.dto.CardDTO;
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
public class CardService {
    private final UserRepository userRepository;
    private final CsvDataLoader csvDataLoader;


    public List<CardDTO> searchUserCards(String token, String category, String query) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getCards().stream().map(Card::convertToCardDTO)
                .filter(card -> category == null || card.getCategory().equalsIgnoreCase(category))
                .filter(card -> query == null ||
                        card.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        card.getQuestion().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
