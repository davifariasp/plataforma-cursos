package com.example.api.dtos.request;

import java.util.UUID;

public record InscricaoRequestDto(
        UUID idCurso,
        String cpf
) {
}
