package com.example.backend.producers;

import com.example.backend.dtos.PessoaDto;
import com.example.backend.dtos.request.CriarPessoaRequestDto;
import com.example.backend.models.Pessoa;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PessoaProducer {
    final RabbitTemplate rabbitTemplate;

    public PessoaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.backend.name}")
    private String routingKey;

    public void publishMessagePessoa(Pessoa pessoa){

        var pessoaDto = new PessoaDto();

        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setNascimento(pessoa.getNascimento());
        pessoaDto.setCpf(pessoa.getCpf());
        pessoaDto.setEmail(pessoa.getEmail());

        rabbitTemplate.convertAndSend("", routingKey, pessoaDto);
    }
}
