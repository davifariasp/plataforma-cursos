package com.example.backend.repositories;

import com.example.backend.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query("SELECT p FROM Pessoa p WHERE p.email = :login OR p.cpf = :login")
    Pessoa findByLogin(String login);

    @Query("SELECT p FROM Pessoa p WHERE p.nome = 'admin' ")
    Optional<Pessoa> findAdmin();
}
