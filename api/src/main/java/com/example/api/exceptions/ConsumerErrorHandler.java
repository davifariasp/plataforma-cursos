package com.example.api.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class ConsumerErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        String nomeFila = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        System.out.println("Fila: " + nomeFila);

        String mensagem = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println("Mensagem: " + mensagem);

        System.out.println("Error: " + t.getCause().getMessage());

        throw new AmqpRejectAndDontRequeueException("Erro ao processar a mensagem");
    }
}
