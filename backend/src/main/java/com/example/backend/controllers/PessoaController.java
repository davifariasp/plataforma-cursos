package com.example.backend.controllers;

import com.example.backend.dtos.request.CriarPessoaRequestDto;
import com.example.backend.dtos.request.LoginRequestDto;
import com.example.backend.services.PessoaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity criarPessoa(@Valid @RequestBody CriarPessoaRequestDto request) {
        return ResponseEntity.ok(pessoaService.criarPessoa(request));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(pessoaService.login(request));
    }
}
