package com.example.api.dtos.response;

import java.util.UUID;

public record CursoResponseDto(
        UUID idCurso,
        String nomeCurso,
        int numeroVagas,
        int numeroInscricoes
) {
}
