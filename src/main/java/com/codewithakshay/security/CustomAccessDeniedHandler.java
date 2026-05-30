package com.codewithakshay.security;

import com.codewithakshay.common.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@NullMarked
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message("Access Denied")
                .error("Forbidden")
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
