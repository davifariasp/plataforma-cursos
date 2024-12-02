package com.example.api.services;

import com.example.api.dtos.request.CriarCursoRequestDto;
import com.example.api.dtos.response.CriarCursoResponseDto;
import com.example.api.dtos.response.CursoResponseDto;
import com.example.api.models.Curso;
import com.example.api.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public CriarCursoResponseDto criarCurso (CriarCursoRequestDto request){
        Curso curso = toEntity(request);
        cursoRepository.save(curso);

        return new CriarCursoResponseDto(curso.getId(), "Curso criado com sucesso");
    }

    public CursoResponseDto buscarCurso(UUID id) {
        return cursoRepository.findById(id).map(this::toResponse).orElse(null);
    }

    public List<CursoResponseDto> listarCursos(){
        List<Curso> cursos = cursoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        return listToResponse(cursos);
    }

    public Map<String, List<Map<String, String>>> listarInscritos(UUID idCurso){
        return cursoRepository.findById(idCurso).map(
                curso -> {
                    List<Map<String, String>> inscritos = curso.getInscricoes().stream()
                            .map(inscricao -> {
                                Map<String, String> inscrito = new HashMap<>();
                                inscrito.put("cpf", inscricao.getPessoa().getCpf());
                                return inscrito;
                            })
                            .collect(Collectors.toList());
                    Map<String, List<Map<String, String>>> response = new HashMap<>();
                    response.put("inscritos", inscritos);
                    return response;
                }).orElse(null);

    }


    List<CursoResponseDto> listToResponse(List<Curso> cursos){
        return cursos.stream().map(this::toResponse).collect(Collectors.toList());

    }

    CursoResponseDto toResponse(Curso curso) {
        return new CursoResponseDto(curso.getId(), curso.getNome(), curso.getNumeroVagas(), curso.getInscricoes().size());
    }


    Curso toEntity(CriarCursoRequestDto request){
        Curso curso = new Curso();
        curso.setNome(request.nome());
        curso.setNumeroVagas(request.numeroVagas());

        return curso;
    }
}
