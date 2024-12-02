package com.example.api.repositories;

import com.example.api.models.Inscricao;
import com.example.api.models.InscricaoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscricaoRepository extends JpaRepository<Inscricao, InscricaoId> {

    List<Inscricao> findAllByCursoId(UUID id);
    List<Inscricao> findAllByPessoaCpf(String cpf);
}
