package com.example.backend.services;

import com.example.backend.dtos.request.CriarPessoaRequestDto;
import com.example.backend.dtos.request.LoginRequestDto;
import com.example.backend.dtos.response.CriarPessoaResponseDto;
import com.example.backend.dtos.response.LoginResponseDto;
import com.example.backend.models.Pessoa;
import com.example.backend.producers.PessoaProducer;
import com.example.backend.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    PessoaProducer pessoaProducer;

    @Transactional
    public CriarPessoaResponseDto criarPessoa(CriarPessoaRequestDto request){
        Pessoa pessoa = toEntity(request);
        var res = pessoaRepository.save(pessoa);

        pessoaProducer.publishMessagePessoa(res);

        return new CriarPessoaResponseDto(res.getId(), "Usuário criado com sucesso");
    }

    public LoginResponseDto login(LoginRequestDto request) {

        Pessoa pessoa = pessoaRepository.findByLogin(request.login());

        if (pessoa == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!passwordEncoder.matches(request.senha(), pessoa.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        Map<String, String> user = Map.of("nome", pessoa.getNome(), "cpf", pessoa.getCpf());

        var claims = JwtClaimsSet.builder()
                .issuer("backend")
                .subject(pessoa.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDto(user, jwtValue, expiresIn);
    }

    public Pessoa toEntity(CriarPessoaRequestDto pessoaDTO) {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.nome());
        pessoa.setNascimento(pessoaDTO.nascimento());
        pessoa.setCpf(pessoaDTO.cpf());
        pessoa.setEmail(pessoaDTO.email());
        pessoa.setSenha(passwordEncoder.encode(pessoaDTO.senha()));

        return pessoa;
    }
}
