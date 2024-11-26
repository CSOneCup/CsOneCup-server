package com.csOneCup.csOneCup.user;

import com.csOneCup.csOneCup.dto.ResponseString;
import com.csOneCup.csOneCup.dto.SignUpRequest;
import com.csOneCup.csOneCup.global.common.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
