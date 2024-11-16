package com.csOneCup.csOneCup.global.error;

import com.csOneCup.csOneCup.global.error.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CustomErrorController implements ErrorController {
    private final ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    @ResponseBody
    public ErrorResponse handlerError(WebRequest request) {
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());

        int status = (int) errorAttributes.getOrDefault("status", 500);
        String message = (String) errorAttributes.getOrDefault("message", "Unexpected error");

        System.out.println("current status : " + status);

        return ErrorResponse.builder()
                .code(status)
                .message(message)
                .build();
    }
}
