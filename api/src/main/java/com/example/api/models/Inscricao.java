package com.example.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inscricao")
@NoArgsConstructor
public class Inscricao {

    @EmbeddedId
    @Getter
    private InscricaoId id;

    @ManyToOne
    @JoinColumn(name = "idCurso", insertable=false, updatable=false)
    @Getter
    @Setter
    private Curso curso;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "idPessoa", insertable=false, updatable=false)
    private Pessoa pessoa;

    public Inscricao(Curso curso, Pessoa pessoa) {
        this.curso = curso;
        this.pessoa = pessoa;
        this.id = new InscricaoId();
        this.id.setIdCurso(curso.getId());
        this.id.setIdPessoa(pessoa.getId());
    }
}
