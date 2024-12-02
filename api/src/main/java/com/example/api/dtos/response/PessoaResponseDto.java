package com.example.api.dtos.response;

import java.util.List;
import java.util.UUID;

public record PessoaResponseDto(
        String nome,
        String email,
        String cpf,
        List<UUID> inscricoes
) {
}
