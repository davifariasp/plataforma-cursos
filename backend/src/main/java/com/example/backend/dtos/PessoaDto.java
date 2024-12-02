package com.example.backend.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PessoaDto implements Serializable {
    private UUID id;
    private String nome;
    private LocalDate nascimento;
    private String cpf;
    private String email;
}
