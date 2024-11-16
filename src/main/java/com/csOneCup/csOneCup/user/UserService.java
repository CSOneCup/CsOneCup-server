package com.csOneCup.csOneCup.user;

import com.csOneCup.csOneCup.dto.ResponseString;
import com.csOneCup.csOneCup.dto.SignUpRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseString signUp(SignUpRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        if(userRepository.existsById(request.getUserId())) throw new DuplicateKeyException("exist user id");
        User user = userRepository.save(
                User.builder()
                        .userId(request.getUserId())
                        .name(request.getName())
                        .password(encodedPassword)
                        .build()
        );

        return ResponseString.builder().response(user.getUserId()).build();
    }
}
