package com.example.backend.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CriarPessoaRequestDto(
    @NotNull String nome,
    LocalDate nascimento,
    String cpf,
    @NotNull String email,
    @NotNull String senha
) { }
