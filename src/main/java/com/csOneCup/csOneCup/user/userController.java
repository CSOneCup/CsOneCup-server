package com.csOneCup.csOneCup.user;

import com.csOneCup.csOneCup.auth.JWTUtil;
import com.csOneCup.csOneCup.dto.ResponseString;
import com.csOneCup.csOneCup.dto.SignInRequest;
import com.csOneCup.csOneCup.dto.SignUpRequest;
import com.csOneCup.csOneCup.global.common.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class userController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> changeUserName(@RequestBody SignUpRequest request) {
        ResponseString response = userService.signUp(request);

        return SuccessResponse.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse<?>> login(@RequestBody SignInRequest signInRequest) {
        ResponseString token = userService.signin(signInRequest);
        return SuccessResponse.ok(token);
    }

    @GetMapping("/info")
    public ResponseEntity<SuccessResponse<?>> getInfo(@RequestHeader("Authorization") String token) {
        return SuccessResponse.ok(userService.getInfo(token));
    }

    @GetMapping("/all/info")
    public ResponseEntity<SuccessResponse<?>> getAllInfo(@RequestHeader("Authorization") String token) {
        return SuccessResponse.ok(userService.getAllInfo(token));
    }
}
