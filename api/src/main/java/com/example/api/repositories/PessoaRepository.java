package com.example.api.repositories;

import com.example.api.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
        Optional<Pessoa> findByCpf(String cpf);
}
