package com.example.api.controllers;

import com.example.api.dtos.request.InscricaoRequestDto;
import com.example.api.repositories.InscricaoRepository;
import com.example.api.services.CursoService;
import com.example.api.services.InscricaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inscricao")
@SecurityRequirement(name = "Authorization")
public class InscricaoController {

    @Autowired
    InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity realizarInsricao(@Valid @RequestBody InscricaoRequestDto request) {
        return ResponseEntity.ok(inscricaoService.inscrever(request));
    }

    @GetMapping("{cpf}")
    public ResponseEntity listarInscricoes(@PathVariable String cpf) {
        return ResponseEntity.ok(inscricaoService.listarInscricoes(cpf));
    }
}
