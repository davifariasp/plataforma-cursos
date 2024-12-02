package com.example.backend.dtos.response;

import java.util.UUID;

public record CriarPessoaResponseDto(
        UUID id,
        String mensagem
) {
}
