package com.csOneCup.csOneCup.user;

import com.csOneCup.csOneCup.auth.JWTUtil;
import com.csOneCup.csOneCup.dto.ResponseString;
import com.csOneCup.csOneCup.dto.SignInRequest;
import com.csOneCup.csOneCup.dto.SignUpRequest;
import com.csOneCup.csOneCup.dto.UserDTO;
import com.csOneCup.csOneCup.global.error.exception.UnauthorizedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

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

    public ResponseString signin(SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUserId(), signInRequest.getPassword())
            );

            return ResponseString.builder().response(jwtUtil.createJWT(signInRequest.getUserId(), 360000000L)).build();
        }
        catch (Exception e) {
            throw new UnauthorizedException();
        }
    }

    public UserDTO getInfo(String token) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDTO.fromEntity(user);
    }

    public List<UserDTO> getAllInfo(String token) {
        String userId = JWTUtil.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> users = userRepository.findAll();

        return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
}
