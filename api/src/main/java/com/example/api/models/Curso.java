package com.example.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Usamos AUTO ou UUID específico para a geração.
    @Column(name = "idCurso")
    @Getter
    private UUID id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private int numeroVagas;

    @Getter
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();

    public boolean decrementarVagas() {
        if (numeroVagas > 0) {
            numeroVagas--;
            return true;
        }
        return false;
    }
}
