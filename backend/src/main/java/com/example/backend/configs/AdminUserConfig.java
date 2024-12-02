package com.example.backend.configs;

import com.example.backend.models.Pessoa;
import com.example.backend.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {


    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    PessoaRepository pessoaRepository;

    public AdminUserConfig(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Optional<Pessoa> userAdmin = pessoaRepository.findAdmin();

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    System.out.println("criando usuário...");

                    var pessoa = new Pessoa();
                    pessoa.setNome("admin");
                    pessoa.setSenha(passwordEncoder.encode("123"));
                    pessoa.setEmail("admin@email.com");
                    pessoa.setCpf("12345678900");
                    pessoaRepository.save(pessoa);
                }
        );
    }
}
