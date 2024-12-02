package com.example.api.controllers;

import com.example.api.dtos.request.CriarCursoRequestDto;
import com.example.api.services.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "Authorization")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping
    public ResponseEntity criarCurso(@Valid @RequestBody CriarCursoRequestDto request) {
        return ResponseEntity.ok(cursoService.criarCurso(request));
    }

    @GetMapping
    public ResponseEntity listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping("/inscritos/{idCurso}")
    public ResponseEntity listarInscritos(@PathVariable UUID idCurso) {
        return ResponseEntity.ok(cursoService.listarInscritos(idCurso));
    }
}
