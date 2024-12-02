package com.example.api.consumers;

import com.example.api.dtos.PessoaDto;
import com.example.api.repositories.PessoaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PessoaConsumer {

    @Autowired
    PessoaRepository pessoaRepository;

    @RabbitListener(queues = "${broker.queue.backend.name}")
    public void listenEmailQueue(@Payload PessoaDto pessoaDto) {
        try {
            var pessoa = pessoaDto.toEntity();
            pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a mensagem", e); // Lançar exceção para acionar o retry
        }
    }
}
