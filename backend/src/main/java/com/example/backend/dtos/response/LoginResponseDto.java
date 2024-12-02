package com.example.backend.dtos.response;

import java.util.Map;

public record LoginResponseDto(
        Map<String, String> user,
        String accessToken,
        Long expiresIn
) {
}
