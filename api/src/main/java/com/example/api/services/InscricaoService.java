package com.example.api.services;

import com.example.api.dtos.request.InscricaoRequestDto;
import com.example.api.dtos.response.CursoResponseDto;
import com.example.api.dtos.response.InscricaoResponseDto;
import com.example.api.models.Curso;
import com.example.api.models.Inscricao;
import com.example.api.models.Pessoa;
import com.example.api.repositories.CursoRepository;
import com.example.api.repositories.InscricaoRepository;
import com.example.api.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InscricaoService {

    @Autowired
    InscricaoRepository inscricaoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    CursoService cursoService;

    @Transactional
    public InscricaoResponseDto inscrever(InscricaoRequestDto request) {
        Curso curso = cursoRepository.findById(request.idCurso()).orElseThrow();
        Pessoa pessoa = pessoaRepository.findByCpf(request.cpf()).orElseThrow();

        if(pessoa.getInscricoes().stream().anyMatch(inscricao -> inscricao.getCurso().getId().equals(curso.getId()))) {
            throw new RuntimeException("Inscrição já realizada");
        }

        if (curso.decrementarVagas()) {
            try {
                Inscricao inscricao = new Inscricao(curso, pessoa);
                var res = inscricaoRepository.save(inscricao);
                cursoRepository.save(curso);

                // Envia a atualização para os clientes via WebSocket
                messagingTemplate.convertAndSend("/topic", cursoService.listarCursos());

                return new InscricaoResponseDto(res.getId(), "Inscrição realizada com sucesso!");
            } catch (Exception e) {
                throw new RuntimeException("Erro ao realizar inscrição");
            }
        } else {
            throw new RuntimeException("Vagas esgotadas");
        }
    }

    public List<UUID> listarInscricoes (String cpf) {
        return inscricaoRepository.findAllByPessoaCpf(cpf).stream().map(inscricao -> {
            return inscricao.getCurso().getId();
        }).collect(Collectors.toList());
    }
}
