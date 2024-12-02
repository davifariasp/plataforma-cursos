package com.example.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pessoa")
@NoArgsConstructor
public class Pessoa {

    @Id
    @Column(name = "idPessoa")
    @Getter
    @Setter
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
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();
}
