package com.example.api.dtos;

import com.example.api.models.Pessoa;

import java.time.LocalDate;
import java.util.UUID;

public record PessoaDto(
        UUID id,
        String nome,
        LocalDate nascimento,
        String cpf,
        String email
) {

    public Pessoa toEntity() {

        Pessoa pessoa = new Pessoa();

        pessoa.setId(this.id());
        pessoa.setNome(this.nome());
        pessoa.setNascimento(this.nascimento());
        pessoa.setCpf(this.cpf());
        pessoa.setEmail(this.email());

        return pessoa;
    }
}
