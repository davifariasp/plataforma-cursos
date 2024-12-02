package com.example.api.dtos.response;

import java.util.UUID;

public record CriarCursoResponseDto(
        UUID idCurso,
        String mensagem){
}
