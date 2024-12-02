package com.example.api.dtos.response;

import com.example.api.models.InscricaoId;


public record InscricaoResponseDto (
        InscricaoId insricaoId,
        String mensagem
) {
}
