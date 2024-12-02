package com.example.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pessoa")
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPessoa")
    @Getter
    private UUID id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private LocalDate nascimento;

    @Getter
    @Setter
    @Column(unique = true)
    private String cpf;

    @Email
    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    @Getter
    @Setter
    private String senha;
}
